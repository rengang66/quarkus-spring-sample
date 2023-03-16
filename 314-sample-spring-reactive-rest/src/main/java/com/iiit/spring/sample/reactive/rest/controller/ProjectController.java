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
package com.iiit.spring.sample.reactive.rest.controller;


import java.util.Set;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;




import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.jboss.logging.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.iiit.spring.sample.reactive.rest.service.ProjectService;
import com.iiit.spring.sample.reactive.rest.domain.Project;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectController.class.getName());
	
	private final ProjectService projectService;	

	public ProjectController( ProjectService projectService) {
		this.projectService = projectService;
	}
	
	/*
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all fruits", description = "Get all fruits")
	@ApiResponse(responseCode = "200", description = "All fruits")
	public Set<Project> list() {
		LOGGER.info("list方法获取全部数据");
		return this.projectService.list();
	}
	*/
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all projects", description = "Get all projects")
	@ApiResponse(responseCode = "200", description = "All projects")
	public Flux<Project> list() {
		return this.projectService.getFruits();
	}
	

	/*
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get a fruit", description = "Get a fruit")
	@ApiResponse(responseCode = "200", description = "Requested fruit")
	@ApiResponse(responseCode = "404", description = "Fruit not found")
	public Project getById(@Parameter(required = true, description = "Fruit name") @PathVariable("id")  Integer id) {		
		return this.projectService.getById(id);
	}
	*/
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get a project", description = "Get a project")
	@ApiResponse(responseCode = "200", description = "Requested project")
	@ApiResponse(responseCode = "404", description = "Project not found")
	public Mono<ResponseEntity<Project>> getById(@Parameter(required = true, description = "Project name") @PathVariable("id") Integer id) {
		return this.projectService.getById(id)
			.map(ResponseEntity::ok)
			.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	

	/*
	@PostMapping(path ="/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Add a new fruit", description = "Add a new fruit")
	@ApiResponse(responseCode = "200", description = "Fruit added")
	public Set<Project> add( Project project) {
		return this.projectService.add(project);
	}
	*/
	
	@PostMapping(path ="/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Add a new project", description = "Add a new project")
	@ApiResponse(responseCode = "200", description = "Project added")
	public Flux<Project> add(@Parameter(required = true, description = "Project to add") @Valid @RequestBody Project project) {
		return this.projectService.add(project);
	}
	

	/*
	@PostMapping(path ="/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "update a fruit", description = "update a new fruit")
	@ApiResponse(responseCode = "200", description = "Fruit added")
	public Set<Project> update(Project project) {
		return this.projectService.update(project);
	}
	*/
	
	@PostMapping(path ="/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Add a new project", description = "Add a new project")
	@ApiResponse(responseCode = "200", description = "Project added")
	public Flux<Project> update(@Parameter(required = true, description = "Project to add") @Valid @RequestBody Project project) {
		return this.projectService.update(project);
	}
	

	/*
	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Delete a fruit", description = "Delete a fruit")
	@ApiResponse(responseCode = "204", description = "Fruit deleted")
	public Set<Project> delete(Project project) {
		return this.projectService.delete(project);
	}
	*/
	
	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Delete a project", description = "Delete a project")
	@ApiResponse(responseCode = "204", description = "Project deleted")
	public Flux<Project> delete(@Parameter(required = true, description = "Project name")@Valid @RequestBody Project project) {
		return this.projectService.delete(project);
	}
	

}