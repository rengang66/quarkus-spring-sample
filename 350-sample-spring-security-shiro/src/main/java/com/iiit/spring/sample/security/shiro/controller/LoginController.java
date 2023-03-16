package com.iiit.spring.sample.security.shiro.controller;

import com.iiit.spring.sample.security.shiro.mapper.UserMapper;
import com.iiit.spring.sample.security.shiro.config.ResultMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final ResultMap resultMap;
    private final UserMapper userMapper;

    @Autowired
    public LoginController(ResultMap resultMap, UserMapper userMapper) {
        this.resultMap = resultMap;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public ResultMap notLogin() {
        return resultMap.success().message("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public ResultMap notRole() {
        return resultMap.success().message("您没有权限！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return resultMap.success().message("成功注销！");
    }
  
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMap login(String username, String password) {        
        Subject subject = SecurityUtils.getSubject();        
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);       
        String role = userMapper.getRole(username);
        if ("user".equals(role)) {
            return resultMap.success().message("欢迎登陆");
        }
        if ("admin".equals(role)) {
            return resultMap.success().message("欢迎来到管理员页面");
        }
        return resultMap.fail().message("权限错误！");
    }
}
