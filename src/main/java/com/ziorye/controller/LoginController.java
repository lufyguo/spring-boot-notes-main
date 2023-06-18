package com.ziorye.controller;

import com.ziorye.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author ziorye
 */
@Controller
public class LoginController {
    @GetMapping("login")
    String showLoginPage() {
        return "login";
    }

    @PostMapping("login")
    String login(User user, HttpSession session, RedirectAttributes redirectAttributes) {
        if (user != null && "secret".equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("msg", "邮箱或密码错误");
        return "redirect:/login";
    }

    @GetMapping("/")
    String home() {
        return "index";
    }
}
