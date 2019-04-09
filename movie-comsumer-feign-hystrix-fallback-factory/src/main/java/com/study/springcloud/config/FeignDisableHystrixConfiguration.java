package com.study.springcloud.config;

import feign.Feign;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootConfiguration
public class FeignDisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuild(){
        Feign.Builder builder = Feign.builder();
        return builder;
    }
}
