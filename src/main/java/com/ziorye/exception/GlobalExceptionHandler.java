package com.ziorye.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ziorye
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public String handleArithmeticException() {
        return "error/handle-arithmetic-exception";
    }
}
