package com.peopletech.config;

import com.peopletech.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Void> handle(Exception e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
