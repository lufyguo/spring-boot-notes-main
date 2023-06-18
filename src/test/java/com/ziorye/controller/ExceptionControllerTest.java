package com.ziorye.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author ziorye
 */
public class ExceptionControllerTest extends TestBaseControllerWithMockHttpSession {
    @Test
    @DisplayName("测试全局异常处理")
    void testGlobalExceptionHandler() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/arithmetic-exception")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.view().name("error/handle-arithmetic-exception"))
        ;
    }

    @Test
    @DisplayName("测试自定义异常")
    void testCustomException() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/custom-exception")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.status().isForbidden())
        ;
    }
}
