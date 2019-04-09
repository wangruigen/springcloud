package com.study.springcloud.feignclient;

import com.study.springcloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * user-provider 微服务提供者的feign客户端。
 * 先创建一个UserFeignClient接口
 */
/*
配置注解FeignClient，指定eureka server列表中的服务，这里是用户提供者服务实例名.path表示的是应用名，可理解成tomcat root path
   里面还有很多参数，简单讲url,url指定服务端地址，这里配合eureka使用，自动从eureka获取服务，可以不写。
 */
@FeignClient(name = "user-provider",path = "/user-provider")
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable(value = "id") String id);

}
