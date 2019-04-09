package com.study.springcloud.controller;

import com.study.springcloud.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {

        return null;
    }
}
