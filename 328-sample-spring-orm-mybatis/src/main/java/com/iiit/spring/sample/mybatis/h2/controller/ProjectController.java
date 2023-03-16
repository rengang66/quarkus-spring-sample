package com.iiit.spring.sample.mybatis.h2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.iiit.spring.sample.mybatis.h2.domains.Project;
import com.iiit.spring.sample.mybatis.h2.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping(value="/getAll", produces=MediaTypes.HAL_JSON_VALUE)
	public List<Project> getAllProject(){		
		List<Project> projects = projectService.getAllProject();		
		return projects;		
	}
	
	@RequestMapping(value="/getProjectById/{id}",method = RequestMethod.GET)
	public Project getProject(@PathVariable Long id){
		logger.info("calling employee service");
		return projectService.getProject(id);
	}
	
	
	@RequestMapping(value="/add", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE )
	public Project createProject(@RequestBody Project project){		
		return projectService.createProject(project);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE )
	public Project updateProject(@RequestBody Project project){		
		return projectService.updateProject(project);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE )
	public boolean deleteProject(@PathVariable Long id){		
		return projectService.deleteProject(id);
	}
	

}
