package com.ziorye;

import com.ziorye.controller.TestBaseControllerWithMockHttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author ziorye
 */
@SpringBootTest
public class RedisTest extends TestBaseControllerWithMockHttpSession {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void testRedis() {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        String key = "hello";
        String value = "redis";
        vo.set(key, value);
        Assertions.assertEquals(value, vo.get(key));
    }

    @Test
    @DisplayName("测试从 redis 中获取 /login 页面的访问次数")
    void testLoginPageViewCountInterceptor() throws Exception {
        String requestURI = "/login";
        redisTemplate.delete(requestURI);

        mvc.perform(MockMvcRequestBuilders.get(requestURI));
        mvc.perform(MockMvcRequestBuilders.get(requestURI));
        mvc.perform(MockMvcRequestBuilders.get(requestURI));

        Assertions.assertEquals("3", redisTemplate.opsForValue().get(requestURI));
    }
}
