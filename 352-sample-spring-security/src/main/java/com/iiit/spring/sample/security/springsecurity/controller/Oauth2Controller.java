package com.iiit.spring.sample.security.springsecurity.controller;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAuthorizationServer
@RequestMapping("/oauth2")
public class Oauth2Controller {
	
	@GetMapping("/annonymous")
	public String getDetails() {
		return "Welcome Oauth2 Annonymous";
	}
	
	@GetMapping("/user")
	public String getUserDetails() {
		return "Welcome Oauth2 User";
	}
	
	@GetMapping("/admin")
	public String getAdminDetails() {
		return "Welcome Oauth2 Admin";
	}

}
