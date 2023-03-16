package com.iiit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiit.spring.sample.service.Project;
import com.iiit.spring.sample.service.ProjectService;
import com.iiit.spring.sample.service.aop.Countable;
import com.iiit.spring.sample.service.aop.TrackTime;


/**
 * Rest API Controller for demo
 *
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {
	
	@Autowired 
	ProjectService projectService;

    @GetMapping
    @TrackTime
    @Countable
    public String helloWorld() {    	
        return "Hello world !!!";
    }    
    
    @GetMapping(path = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    @TrackTime
    @Countable
    public List<Project>  getList() {
    	return projectService.getAllProject();    	
    }
    
    @GetMapping(path = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @TrackTime
    @Countable
    public Project getById( @PathVariable("id")  Integer id) {    	
    	return this.projectService.getProjectById(id);
    }
}
