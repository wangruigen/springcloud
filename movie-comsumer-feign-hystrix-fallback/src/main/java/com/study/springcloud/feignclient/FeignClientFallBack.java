package com.study.springcloud.feignclient;

import com.study.springcloud.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FeignClientFallBack implements UserFeignClient {
    @Override
    public User findById(String id) {
        User user = new User();
        user.setId(10000L);
        user.setUsername("default_username");
        user.setName("defalut_name");
        user.setAge(0);
        user.setBalance(new BigDecimal(0));
        return user;
    }
}
