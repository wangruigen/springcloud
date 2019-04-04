package com.study.springcloud.controller;

import com.study.springcloud.entity.User;
import com.study.springcloud.feignclient.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
    /**
     * MovieController 通过注入UserFeignCLient的方式调用http api
     */
    private UserFeignClient userFeignClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    public MovieController(Client client, Decoder decoder, Encoder encoder, Contract contract) {
        this.userFeignClient = Feign.builder().
                        client(client).
                        encoder(encoder).
                        decoder(decoder).
                        contract(contract).
                        requestInterceptor(new BasicAuthRequestInterceptor("user","password1")).
                        //url: http://serviceId/servet-content-path/
                        target(UserFeignClient.class,"http://user-provider-auth/user-provider-auth/");

        this.adminFeignClient = Feign.builder().
                client(client).
                encoder(encoder).
                decoder(decoder).
                contract(contract).
                requestInterceptor(new BasicAuthRequestInterceptor("admin","password2")).
                //url: http://serviceId/servet-content-path/
                target(UserFeignClient.class,"http://user-provider-auth/user-provider-auth/");
    }

    @GetMapping("/user-user/{id}")
    public User getUserById(@PathVariable String id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/user-admin/{id}")
    public User getUserById2(@PathVariable String id) {
        return adminFeignClient.findById(id);
    }
}
