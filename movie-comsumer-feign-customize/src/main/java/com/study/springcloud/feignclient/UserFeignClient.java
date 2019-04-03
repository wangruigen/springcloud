package com.study.springcloud.feignclient;

import com.study.springcloud.MyFeignConfiguration;
import com.study.springcloud.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * user-provider 微服务提供者的feign客户端。
 * 先创建一个UserFeignClient接口
 */
/*
配置注解FeignClient，指定eureka server列表中的服务，这里是用户提供者服务实例名.path表示的是应用名，可理解成tomcat root path
   里面还有很多参数，简单讲url,url指定服务端地址，这里配合eureka使用，自动从eureka获取服务，可以不写。
configuration:配置自己自定义的feign client
 */
@FeignClient(name = "user-provider",path = "/user-provider",configuration = MyFeignConfiguration.class)
public interface UserFeignClient {

    /*@RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable(value = "id") String id);*/

    /**
     * 使用feign自带的注解标识方法 @RequestLine
     * @param id
     * @return
     */
    @RequestLine("GET /user/{id}")
    User findById(@Param("id")String id);

}
