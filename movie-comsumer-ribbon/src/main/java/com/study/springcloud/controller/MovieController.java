package com.study.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.study.springcloud.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    private Logger log = LoggerFactory.getLogger(MovieController.class);

    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public MovieController(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    @HystrixCommand(fallbackMethod = "getUserByIdFallBack")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        String url = "http://user-provider/user/" + id;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        return response.getBody();
    }

    /**
     * 出现短路请求返回的缺省值
     *
     * @param id
     * @return
     */
    public User getUserByIdFallBack(String id, Throwable throwable) {
        log.info("进入getUserByIdFallBack(..)回退方法，异常：" + throwable);
        User user = new User();
        user.setId(10000L);
        user.setName("default user");
        return user;
    }


    /**
     * 出现短路，不会退
     * 出现 int a = 1/0--》ArithmeticException异常是不回退
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserByIdFallBack", ignoreExceptions = {ArithmeticException.class})
    @GetMapping("/noback/user/{id}")
    public User notFallBack(@PathVariable String id) {
        int a = 1/0;
        String url = "http://user-provider/user-provider/user/" + id;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        return response.getBody();
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance() {
        //测试的时候启动多个user-provider-metadata实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider-metadata");
        log.info("查看是哪个节点访问");
        log.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }
}
