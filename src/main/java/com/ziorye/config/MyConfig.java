package com.ziorye.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ziorye
 */
@Configuration
@EnableConfigurationProperties(MyProperties.class)
public class MyConfig {
}
