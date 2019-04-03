package com.study.springcloud.controller;

import com.study.springcloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id){
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8088/user-provider/user/"+id, User.class);
        return response.getBody();
    }
}
