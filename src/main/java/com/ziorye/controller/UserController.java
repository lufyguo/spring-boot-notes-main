package com.ziorye.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziorye.bean.User;
import com.ziorye.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author ziorye
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users")
    public String index(@RequestParam(value = "p", defaultValue = "1") Integer p, Model model) {
        Page<User> page = userService.page(new Page<>(p, 3));
        model.addAttribute("page", page);
        return "users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id, @RequestParam(value = "p", defaultValue = "1") Integer p, RedirectAttributes redirectAttributes) {
        userService.removeById(id);
        redirectAttributes.addAttribute("p", p);
        return "redirect:/users";
    }

    @PostMapping("upload")
    public String upload(
            @RequestParam("name") String name,
            @RequestPart("avatar") MultipartFile avatar,
            @RequestPart("photos") MultipartFile[] photos,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("avatar", avatar.getOriginalFilename());
        model.addAttribute("photos", photos.length);
        log.info(model.toString());
        return "index";
    }
}
