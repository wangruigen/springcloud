package com.study.springcloud.feignclient;

import com.study.springcloud.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFallBackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger log = LoggerFactory.getLogger(FeignClientFallBackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        UserFeignClient result = new UserFeignClient() {
            @Override
            public User findById(String id) {
                log.error("fallback message is "+throwable);
                User user = new User();
                user.setId(1000L);
                user.setName("defaultName");
                return user;
            }
        };
        return result;
    }
}
