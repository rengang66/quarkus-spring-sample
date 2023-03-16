package com.iiit.spring.sample.mybatis.h2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiit.spring.sample.mybatis.h2.domains.Project;
import com.iiit.spring.sample.mybatis.h2.mapper.ProjectMapper;


@Service
public class ProjectServiceImpl implements ProjectService   {

	private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class); 	
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public Project createProject(Project project) {
		this.projectMapper.createProject(project);
		return project;
	}

	@Override
	public Project getProject(Long empId) {
		return this.projectMapper.getProjectById(empId);
	}

	@Override
	public List<Project> getAllProject() {
		return this.projectMapper.getAllProject();
	}

	@Override
	public Project updateProject(Project project) {
		this.projectMapper.updateProject(project);
		return project;
	}

	@Override
	public boolean deleteProject(Long empId) {
		return this.projectMapper.deleteProject(empId);
	}

}
