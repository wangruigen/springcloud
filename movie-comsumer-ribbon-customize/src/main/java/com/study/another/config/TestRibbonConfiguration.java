package com.study.another.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * <p>使用RibbonClient，为特定name的Ribbon Client自定义配置</p>
 * <p>使用RibbonClient的configuretion属性，指定ribbon的配置类</p>
 *
 */
@Configuration
@RibbonClient(name = "user-provider-metadata",configuration = RibbonConfiguration.class)
public class TestRibbonConfiguration {
}
