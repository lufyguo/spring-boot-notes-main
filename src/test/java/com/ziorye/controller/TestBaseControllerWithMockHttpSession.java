package com.ziorye.controller;

import com.ziorye.bean.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author ziorye
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TestBaseControllerWithMockHttpSession {
    @Autowired
    public MockMvc mvc;

    public MockHttpSession session;

    @BeforeEach
    public void initSession() {
        this.session = new MockHttpSession();
        session.setAttribute("loginUser", new User("userName", 18, "test@test.com", "secret"));
    }
}
