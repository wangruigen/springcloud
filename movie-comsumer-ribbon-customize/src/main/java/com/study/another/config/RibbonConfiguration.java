package com.study.another.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon配置类，不在springboot扫描范围内
 */
@Configuration
public class RibbonConfiguration {

    /**
     * 定义一个负债均衡规则：随机访问
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
