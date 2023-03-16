/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iiit.spring.sample.mongodb.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jboss.logging.Logger;

import com.iiit.spring.sample.mongodb.exception.ResourceNotFoundException;
import com.iiit.spring.sample.mongodb.repository.ProjectRepository;
import com.iiit.spring.sample.mongodb.domain.Project;

@Service
public class ProjectService {	
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getName());
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	private Set<Project> projects = Collections.newSetFromMap(Collections
			.synchronizedMap(new LinkedHashMap<>()));

	public ProjectService() {
		
		//projects.add(new Project(1, "项目A", "关于项目A的情况描述"));
		//projects.add(new Project(2, "项目B", "关于项目B的情况描述"));		
	}
	
	private void initData(){
		LOGGER.info("初始化数据！");
		this.projectRepository.deleteAll();
		Project project1 = new Project("项目A", "关于项目A的描述");
		Project project2 = new Project("项目B", "关于项目B的描述");
		add(project1);
		add(project2);
	}
	
	
	public List<Project> list() {		
		return this.projectRepository.findAll();
	}

	public Project getById(Integer id) throws ResourceNotFoundException {		
		Project project = this.projectRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Project not found for this id :: " + id));
		return project;
	}

	public Project add(Project project) {
		project.id = (int)sequenceGeneratorService.generateSequence(Project.SEQUENCE_NAME);
		this.projectRepository.save(project);
		return project;
	}

	public Project update(Project project) throws ResourceNotFoundException {		
		this.delete(project);
		//this.add(project);	
		this.projectRepository.save(project);
		return project;
	}

	public Project delete(Project project) throws ResourceNotFoundException {		
		if  ( this.getById(project.id) !=  null ){
			this.projectRepository.delete(project);
		}		
		return project;
	}

}