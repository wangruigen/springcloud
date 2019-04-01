package com.study.springcloud.controller;

import com.study.springcloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MovieController {

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    @Autowired
    public MovieController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id){
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:9000/user-provider/user/"+id, User.class);
        return response.getBody();
    }

    /**
     * 查询user-provider-metadata微服务的信息
     * @return 返回user-provider-metadata微服务的信息
     */
    @GetMapping("/user-provider-instance")
    public List<ServiceInstance> showInfo(){
        return discoveryClient.getInstances("user-provider-metadata");
    }
}
