package com.iiit.spring.sample.security.shiro.config;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();       
        shiroFilterFactoryBean.setSecurityManager(securityManager);       
        shiroFilterFactoryBean.setLoginUrl("/notLogin");        
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();        
        filterChainDefinitionMap.put("/guest/**", "anon");        
        filterChainDefinitionMap.put("/user/**", "anon");        
        filterChainDefinitionMap.put("/admin/**", "anon");       
        filterChainDefinitionMap.put("/login", "anon");       
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);       
        return shiroFilterFactoryBean;
    }
    
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();       
        securityManager.setRealm(customRealm);
        return securityManager;
    }
}