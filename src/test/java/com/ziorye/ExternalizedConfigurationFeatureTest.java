package com.ziorye;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ziorye
 */
@SpringBootTest
public class ExternalizedConfigurationFeatureTest {
    @Value("${testExternalApplicationPropertiesFeature}")
    String value;

    @Test
    @DisplayName("测试 classpath:/config 目录下的 application.properties 优先级高于 classpath: 下的 application.properties")
    void testExternalApplicationPropertiesFeature() {
        Assertions.assertEquals("application.properties from the classpath /config package", value);
    }
}
