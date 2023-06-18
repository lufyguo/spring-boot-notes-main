package com.ziorye.controller;

import com.ziorye.config.MyProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ziorye
 */
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Test
    @DisplayName("测试 hello 页面")
    void testHello(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Hello World!"));
    }

    @Test
    @DisplayName("测试属性与配置文件绑定")
    void testMyProperties(@Autowired MockMvc mvc, @Autowired MyProperties myProperties) throws Exception {
        mvc.perform(get("/my")).andExpect(status().isOk()).andExpect(content().string(myProperties.getName() + ", " + myProperties.getAge()));
    }

    @Test
    @DisplayName("测试 yaml 配置文件")
    void testYaml(@Autowired MockMvc mvc) throws Exception {
        String expected = "{\"userName\":\"zhangsan\",\"boss\":false,\"birth\":\"1888-08-08T08:08:09.000+08:00\",\"age\":18,\"interests\":[\"篮球\",\"游泳\"],\"animal\":[\"jerry\",\"mario\"],\"score\":{\"english\":{\"first\":30,\"second\":40,\"third\":50},\"math\":{\"0\":131,\"1\":140,\"2\":148},\"chinese\":{\"first\":128,\"second\":136}},\"salaries\":[3999.0,4999.98,5999.99],\"pet\":{\"name\":\"tomcat\",\"weight\":23.4},\"allPets\":{\"sick\":[{\"name\":\"tom\",\"weight\":null},{\"name\":\"jerry\",\"weight\":47.0}],\"health\":[{\"name\":\"mario\",\"weight\":47.0}]}}";
        mvc.perform(get("/yaml")).andExpect(status().isOk()).andExpect(content().json(expected));
    }
}
