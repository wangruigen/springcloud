package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
/*
服务启动时，不将服务注册到注册中心eureka server。
或者在配置文件中配置spring.cloud.service-registry.auto-registration.enable=false
 */
@EnableDiscoveryClient(autoRegister = true)
public class SpringCloudStydy {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStydy.class,args);
    }
}
