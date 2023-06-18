package com.ziorye.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author ziorye
 */
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("测试登录页面")
    void testShowLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("测试登录成功")
    void testLoginSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "test@test.com")
                .param("password", "secret")
        )
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("loginUser", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
        ;
    }

    @Test
    @DisplayName("测试登录失败")
    void testLoginFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "test@test.com")
                .param("password", "incorrect-password")
        )
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("loginUser", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"))
                .andExpect(MockMvcResultMatchers.flash().attribute("msg", "邮箱或密码错误"))
        ;
    }

    @Test
    @DisplayName("测试未登录访问首页")
    void testVisitHomePageWithoutLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/")
        )
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("loginUser", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.request().attribute("msg", "未登录"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/login"))
        ;
    }
}
