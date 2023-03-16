package com.iiit.spring.sample.security.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.iiit.spring.sample.security.shiro.mapper")
public class SpringShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShiroApplication.class, args);
	}
}
