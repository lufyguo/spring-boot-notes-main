package com.ziorye.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author ziorye
 */
public class UserControllerTest extends TestBaseControllerWithMockHttpSession {
    @Test
    @DisplayName("测试用户列表页面")
    void testUserIndex() throws Exception {
        String currentPage = "2";
        mvc.perform(MockMvcRequestBuilders
                .get("/users")
                .param("p", currentPage)
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.view().name("users"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("page"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("当前第 " + currentPage + " 页")))
        ;
    }
    @Test
    @DisplayName("测试用户删除")
    void testDeleteUser() throws Exception {
        String userId = "5"; // 由于每页显示 3 条记录，所以 id=5 的用户在第 2 页显示
        String currentPage = "2";
        mvc.perform(MockMvcRequestBuilders
                .delete("/users/" + userId)
                .param("p", currentPage)
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.view().name("redirect:/users"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users?p=" + currentPage))
        ;
    }

    @Test
    void testUpload() throws Exception {
        MockMultipartFile avatarFile = new MockMultipartFile("avatar", "avatar.png", MediaType.IMAGE_PNG.getType(), "avatar.png".getBytes());
        MockMultipartFile firstFile = new MockMultipartFile("photos", "photo-1.png", MediaType.IMAGE_PNG.getType(), "photo-1.png".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("photos", "photo-2.png", MediaType.IMAGE_PNG.getType(), "photo-2.png".getBytes());

        mvc.perform(MockMvcRequestBuilders
                .multipart("/upload")
                .file(avatarFile) // avatar
                .file(firstFile) // photos[]
                .file(secondFile) // photos[]
                .param("name", "userName")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("name", "userName"))
                .andExpect(MockMvcResultMatchers.model().attribute("avatar", "avatar.png"))
                .andExpect(MockMvcResultMatchers.model().attribute("photos", 2))
        ;
    }
}
