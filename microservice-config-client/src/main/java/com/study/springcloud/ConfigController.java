package com.study.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    /**
     * 访问配置文件http://localhost:8180/master/application-dev.yml
     * 的值如下:
     * app:
     *   profile: test
     *   version: 1.0
     */
    //取值
    @Value("${app.profile}")
    private String profile;
    @Value("${app.version}")
    private String version;

    @GetMapping("/profile")
    public String profile(){
        return "app.profile="+profile+",app.version="+version;
    }
}
