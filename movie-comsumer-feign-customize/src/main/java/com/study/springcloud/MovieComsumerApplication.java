package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //开启feignClient
public class MovieComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieComsumerApplication.class, args);
    }

}
