package com.ziorye.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ziorye
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "抛出该异常的原因描述")
public class CustomException extends RuntimeException{
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
}
