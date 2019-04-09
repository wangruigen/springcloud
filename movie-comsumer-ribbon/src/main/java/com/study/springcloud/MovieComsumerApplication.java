package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
//@EnableHystrix //开启断路器支持,或者下面的注解也可以
@EnableCircuitBreaker
public class MovieComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieComsumerApplication.class, args);
    }

}
