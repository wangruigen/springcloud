package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients //开启feignClient
public class MovieComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieComsumerApplication.class, args);
    }

}
