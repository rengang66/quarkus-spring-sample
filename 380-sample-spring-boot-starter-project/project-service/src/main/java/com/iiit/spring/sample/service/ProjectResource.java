package com.iiit.spring.sample.service;

public class ProjectResource {	
	
	private ProjectService service;
	public ProjectResource(ProjectService service) {
		this.service = service;
	}    
}