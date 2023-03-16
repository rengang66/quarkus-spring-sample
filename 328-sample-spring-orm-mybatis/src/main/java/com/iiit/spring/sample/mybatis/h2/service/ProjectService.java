package com.iiit.spring.sample.mybatis.h2.service;

import java.util.List;


import com.iiit.spring.sample.mybatis.h2.domains.Project;


public interface ProjectService {

	public Project createProject( Project project );
	
	public Project getProject(Long empId);
	
	public List<Project> getAllProject();
	
	public Project updateProject( Project project );
	
	public boolean deleteProject(Long Id);
}
