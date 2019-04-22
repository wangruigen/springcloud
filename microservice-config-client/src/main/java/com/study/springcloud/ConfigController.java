package com.study.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * @RefreshScope 该注解表示刷新配置时会做处理
 * 手动刷新git仓库的值：通过post 访问config client的/refresh断点进行手动刷新git仓库的配置值。
 */
@RefreshScope
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
