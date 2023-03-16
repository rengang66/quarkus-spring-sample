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
package com.iiit.spring.sample.event.controller;


import java.util.Set;

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

import javax.validation.Valid;

import reactor.core.publisher.Mono;

import com.iiit.spring.sample.event.service.ProjectGateway;
import com.iiit.spring.sample.event.domain.Project;

@RestController
@RequestMapping("/projects")
public class ProjectController {	
	
	private final ProjectGateway projectGateway;

	public ProjectController(ProjectGateway projectGateway) {
		this.projectGateway = projectGateway;
	}	
		
	
	@GetMapping(path = "/all/{all}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public Set<Project> list(@PathVariable String all) {	
		return this.projectGateway.list(all);
	}

	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public Project getById( @PathVariable("id")  Integer id) {		
		return this.projectGateway.getProjectById(id);
	}

	@PostMapping(path ="/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Project add( @Valid @RequestBody Project project) {
		return this.projectGateway.addProject(project);
	}
	
	@PostMapping(path ="/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Set<Project> update( @Valid @RequestBody Project project) {		
		return this.projectGateway.updateProject(project);
	}

	@DeleteMapping("/delete")	
	//@ResponseStatus(HttpStatus.NO_CONTENT)	
	public Set<Project> delete(@Valid @RequestBody Project project) {
		return this.projectGateway.deleteProject(project);
	}
	
	//测试使用
	
	@GetMapping(path = "/async/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	public Mono<String> greeting(@PathVariable String name) {
		return this.projectGateway.greeting(Mono.justOrEmpty(name));
	}
	
	
	@GetMapping(path ="/addone/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Project addone( @PathVariable String name) {
		return this.projectGateway.addProject(name);
		//return (new Project(3, "项目B3", name));
	}
	
	

}