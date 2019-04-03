package com.study.springcloud;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类是自定义的feign配置类，不在springboot扫描的范围内，不然配置类中的
 * feign.Decoder,feign.Encode,feign.Contract等配置会被所有的@feignClient共享。
 * 解决办法：要么不配置@Configuration注解，要么配置类不放在springboot扫描的范围上
 */
//@Configuration
public class MyFeignConfiguration {

    /**
     * 将契约修改成feign原生的默认契约，这样就可以使用feign自带的注解
     * @return 默认的feign契约
     */
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }
}
