package com.ziorye.controller;

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
public class ContentNegotiationControllerTest {
    @Test
    @DisplayName("测试 Content Negotiation 基于请求头的内容协商策略。客服端指定 Accept=application/xml 服务器会自动将原本的 json 格式 Person 转化为 xml 格式的 Person")
    void testHeaderContentNegotiationStrategy(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/getPerson")
                .accept(MediaType.APPLICATION_XML)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("/Person/userName").string("John"))
                .andExpect(MockMvcResultMatchers.xpath("/Person/age").string("18"))
                .andExpect(MockMvcResultMatchers.xpath("/Person/pet/name").string("tomcat"))
                .andExpect(MockMvcResultMatchers.xpath("/Person/pet/weight").string("5.5"))
        ;
    }

    @Test
    @DisplayName("测试 Content Negotiation 基于请求参数的内容协商策略。访问地址附带 format=json 请求参数")
    void testParameterContentNegotiationStrategyWithFormatJson(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/getPerson")
                .queryParam("format", "json")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        ;
    }

    @Test
    @DisplayName("测试 Content Negotiation 基于请求参数的内容协商策略。访问地址附带 format=xml 请求参数")
    void testParameterContentNegotiationStrategyWithFormatXml(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/getPerson")
                .queryParam("format", "xml")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
        ;
    }

    @Test
    @DisplayName("测试 Content Negotiation 添加自定义 HttpMessageConverter")
    void testCustomMessageConverter(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/getPerson")
                .header("Accept", "application/x-custom")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("John;18;other attributes are omitted"))
        ;
    }
}
