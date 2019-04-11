package com.study.springcloud.controller;

import com.study.springcloud.entity.User;
import com.study.springcloud.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    /**
     * MovieController 通过注入UserFeignCLient的方式调用http api
     */
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {

        return userFeignClient.findById(id);
    }
}
