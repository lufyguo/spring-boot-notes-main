package com.ziorye.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ziorye
 */
@ConfigurationProperties(prefix = "my")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@Slf4j
public class MyProperties {
    private String name;
    private int age;
}
