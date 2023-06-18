package com.ziorye.controller;

import com.ziorye.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziorye
 */
@RestController
public class ExceptionController {
    @GetMapping("arithmetic-exception")
    public String arithmeticException() {
        int i = 1/0;
        return "arithmeticException";
    }

    @GetMapping("custom-exception")
    public void customException() {
        throw new CustomException();
    }
}
