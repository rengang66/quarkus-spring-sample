package com.iiit.spring.sample.security.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class SecurityController {
	
	@GetMapping("/annonymous")
	public String getDetails() {
		return "Welcome Annonymous";
	}
	
	@GetMapping("/user")
	public String getUserDetails() {
		return "Welcome User";
	}
	
	@GetMapping("/admin")
	public String getAdminDetails() {
		return "Welcome Admin";
	}

}
