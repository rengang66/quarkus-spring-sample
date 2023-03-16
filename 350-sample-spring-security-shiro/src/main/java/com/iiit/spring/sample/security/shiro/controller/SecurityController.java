package com.iiit.spring.sample.security.shiro.controller;

import com.iiit.spring.sample.security.shiro.config.ResultMap;
import com.iiit.spring.sample.security.shiro.mapper.UserMapper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SecurityController {
    private final ResultMap resultMap;
    private final UserMapper userMapper;

    @Autowired
    public SecurityController(ResultMap resultMap, UserMapper userMapper) {
        this.resultMap = resultMap;
        this.userMapper =  userMapper;
    }
    
    @RequestMapping(value = "/guest/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return resultMap.success().message("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/guest/getMessage", method = RequestMethod.GET)
    public ResultMap submitLogin() {
        return resultMap.success().message("您拥有获得该接口的信息的权限！");
    } 

    @RequestMapping(value = "/admin/getMessage", method = RequestMethod.GET)
    public ResultMap getAdmMessage(String username, String password) {    	
    	String role = authentication(username, password);    	
    	if ("admin".equals(role)) {
            return resultMap.success().message("可以进行管理员操作！！");
        }
        return resultMap.fail().message("权限错误！");    	   
    }        
    
    @RequestMapping(value = "/user/getMessage", method = RequestMethod.GET)
    public ResultMap getUserMessage(String username, String password) {
    	String role = authentication(username, password);    	
    	if ("user".equals(role)) {
            return resultMap.success().message("可以进行一般用户操作！！");
        }
        return resultMap.fail().message("权限错误！");  
    }  
    
    private String authentication (String username, String password) {       
        Subject subject = SecurityUtils.getSubject();       
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);        
        subject.login(token);       
        return  userMapper.getRole(username);        
    }        
}
