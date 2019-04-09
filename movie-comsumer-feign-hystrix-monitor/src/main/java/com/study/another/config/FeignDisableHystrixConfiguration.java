package com.study.another.config;

import feign.Feign;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 配置在springbooot扫描不到的位置
 */
@Configuration
public class FeignDisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuild(){
        Feign.Builder builder = Feign.builder();
        return builder;
    }
}
