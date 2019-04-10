package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //开启zuul代理
public class ZuulBootRun {
    public static void main(String[] args) {
        SpringApplication.run(ZuulBootRun.class, args);
    }
}
