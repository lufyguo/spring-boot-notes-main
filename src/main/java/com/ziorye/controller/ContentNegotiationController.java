package com.ziorye.controller;

import com.ziorye.bean.Person;
import com.ziorye.bean.Pet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziorye
 */
@RestController
public class ContentNegotiationController {
    @GetMapping("getPerson")
    public Person getPerson() {
        Person person = new Person();
        person.setUserName("John");
        person.setAge(18);
        person.setPet(new Pet("tomcat", 5.5));
        return person;
    }
}
