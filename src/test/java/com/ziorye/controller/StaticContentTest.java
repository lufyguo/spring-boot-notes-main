package com.ziorye.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ziorye
 */
@SpringBootTest
@AutoConfigureMockMvc
public class StaticContentTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试静态资源")
    void testStaticContent() throws Exception {
        mvc.perform(get("/resources/test.png")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("测试 webjars 资源访问")
    void testWebjarsContent() throws Exception {
        mvc.perform(get("/webjars/bootstrap/5.0.1/css/bootstrap.min.css")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("测试使用与版本无关的 URL 访问 webjars 资源")
    void testVersionAgnosticURLsForWebjars() throws Exception {
        mvc.perform(get("/webjars/bootstrap/css/bootstrap.min.css")).andExpect(status().isOk());
    }
}
