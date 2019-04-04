package com.study.springcloud.controller;

import com.study.springcloud.entity.User;
import com.study.springcloud.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {
    private Logger log  = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            System.out.println("打印当前用户登录信息");
            for (GrantedAuthority authority : authorities) {
                log.info("当前登录的用户是{},角色是{}",user.getUsername(),user.getAuthorities());
            }
        }else {
            log.info("no more user login messages");
        }
        return userRepository.findOne(id);
    }

}
