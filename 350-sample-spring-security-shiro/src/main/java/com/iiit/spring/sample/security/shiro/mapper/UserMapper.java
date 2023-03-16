package com.iiit.spring.sample.security.shiro.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    
    String getPassword(String username);
    
    String getRole(String username);
}
