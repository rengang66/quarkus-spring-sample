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
package com.iiit.spring.sample.orm.hibernate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiit.spring.sample.orm.hibernate.repository.ProjectRepository;
import com.iiit.spring.sample.orm.hibernate.domain.Project;


@RestController
@RequestMapping("/projects")
public class ProjectController {
	private final ProjectRepository projectRepository;

	public ProjectController(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getAll() {
		return this.projectRepository.findAll();
	}
		
	@RequestMapping(path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public  Project getById( @PathVariable long id ){
		Project project = this.projectRepository.getById(id);
        if ( null == project )
            return null;
        return project;
    }

	@PostMapping(path ="/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Project add(@Valid @RequestBody Project project) {
		return this.projectRepository.save(project);
	}
    
    @PostMapping(path ="/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Project update(@Valid @RequestBody Project project) {    	
		return this.projectRepository.save(project);
	}    
    
    @DeleteMapping(path ="/delete",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
	public List<Project> delete( @Valid @RequestBody Project project) {
    	this.projectRepository.deleteById((long)project.getId());
    	return this.projectRepository.findAll();
	}	
	
}
