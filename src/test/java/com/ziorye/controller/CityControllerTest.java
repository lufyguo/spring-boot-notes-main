package com.ziorye.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author ziorye
 */
public class CityControllerTest extends TestBaseControllerWithMockHttpSession {
    @Test
    @DisplayName("测试 Mybatis 纯注解")
    void testFindByIdUsingMybatis() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/city/1")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("San Francisco"))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value("CA"))
                .andExpect(MockMvcResultMatchers.jsonPath("country").value("US"))
        ;
    }

    @Test
    @DisplayName("测试 Mybatis Mapper 配置文件")
    void testInsertCityUsingMybatis() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/city")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "xm")
                .param("state", "fj")
                .param("country", "CN")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("xm"))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value("fj"))
                .andExpect(MockMvcResultMatchers.jsonPath("country").value("CN"))
        ;
    }
}
