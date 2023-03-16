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
package com.iiit.spring.sample.mongodb.controller;


import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.iiit.spring.sample.mongodb.exception.ResourceNotFoundException;
import org.jboss.logging.Logger;

import com.iiit.spring.sample.mongodb.service.ProjectService;
import com.iiit.spring.sample.mongodb.domain.Project;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectController.class.getName());
	
	private final ProjectService projectService;	

	public ProjectController( ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	public List<Project> list() {
		LOGGER.info("list方法获取全部数据");
		return this.projectService.list();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Project> getById( @PathVariable("id")  Integer id) throws ResourceNotFoundException {
		return ResponseEntity.ok().body(this.projectService.getById(id));		
	}

	@PostMapping(path ="/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Project add( @Valid @RequestBody Project project) {
		
		return this.projectService.add(project);
	}

	@PostMapping(path ="/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Project update( @Valid @RequestBody Project project) throws ResourceNotFoundException {		
		return this.projectService.update(project);
	}

	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public Project delete( @Valid @RequestBody Project project) throws ResourceNotFoundException {
		return this.projectService.delete(project);
	}

}