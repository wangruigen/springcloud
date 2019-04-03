package com.study.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class RestTemplateConfig {

    private final RestTemplateBuilder builder;

    @Autowired
    public RestTemplateConfig(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    /**
     * 电影微服务-服务消费者-整合ribbon，在RestTemplate配置注解@LoadBalanced,使其具备负载均衡能力
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return builder.build();
    }
}
