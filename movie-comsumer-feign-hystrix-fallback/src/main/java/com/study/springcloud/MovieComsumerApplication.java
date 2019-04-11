package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableFeignClients //开启feignClient

@EnableHystrixDashboard //开启hystrix dashboard
public class MovieComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieComsumerApplication.class, args);
    }

}
