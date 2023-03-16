package com.iiit.spring.sample.security.shiro.config;

import com.iiit.spring.sample.security.shiro.config.ResultMap;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    private final ResultMap resultMap;

    @Autowired
    public ExceptionAdvice(ResultMap resultMap) {
        this.resultMap = resultMap;
    }
    
    @ExceptionHandler(AccountException.class)
    public ResultMap handleShiroException(Exception ex) {
        return resultMap.fail().message(ex.getMessage());
    }
}
