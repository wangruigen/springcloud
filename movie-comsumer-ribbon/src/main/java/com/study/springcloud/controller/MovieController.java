package com.study.springcloud.controller;

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

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id){
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8088/user-provider/user/"+id, User.class);
        return response.getBody();
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        //测试的时候启动多个user-provider-metadata实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider-metadata");
        log.info("查看是哪个节点访问");
        log.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }
}
