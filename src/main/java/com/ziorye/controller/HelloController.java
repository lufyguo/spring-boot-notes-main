package com.ziorye.controller;

import com.ziorye.bean.Person;
import com.ziorye.config.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziorye
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    @Autowired MyProperties myProperties;
    @GetMapping("/my")
    String myProperties() {
        return myProperties.getName() + ", " + myProperties.getAge();
    }

    @Autowired
    Person person;
    @GetMapping("/yaml")
    Person yaml() {
        return person;
    }
}
