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
package com.iiit.spring.sample.reactive.data.controller;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import org.jboss.logging.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.iiit.spring.sample.reactive.data.domain.Project;
import com.iiit.spring.sample.reactive.data.repository.ProjectRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectController.class.getName());
	
	private final ProjectRepository projectRepository;

	public ProjectController( ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	public Flux<Project> list() {		
		return this.projectRepository.findAll();
	}	
	
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public Mono<ResponseEntity<Project>> getById( @PathVariable Integer id) {
		
		//org.springframework.data.repository.CrudRepositoryExtensions
		
		return this.projectRepository.findById(id)
			.map(ResponseEntity::ok)
			.defaultIfEmpty(ResponseEntity.notFound().build());
	}	
	
	
	//@PostMapping(path ="/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PostMapping("/add")
	//@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/add")
	//@Transactional
	public Mono<Project> add(@Valid @RequestBody Project project) {
		//project.setId(8);
		//Project project1= new Project(10,"内容123");
		//project.setId(null);
		System.out.println("project.getId:"+project.getId());
		System.out.println("project.getName:"+project.getName());		
		return this.projectRepository.save(project);		
	}
		
	
	@PutMapping(path ="/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)	
	@Transactional
	public Mono<Project> update(@Valid @RequestBody Project project) {
		System.out.println("project.getId:"+project.getId());
		System.out.println("project.getName:"+project.getName());
		this.projectRepository.deleteById(project.getId());	
		System.out.println("================ spring boot  ================");
		System.out.println("project.getId:"+project.getId());
		System.out.println("project.getName:"+project.getName());
		return this.projectRepository.save(project);		
	}
	
	/*
	@PutMapping("/{id}")
    public Mono<Project> update(@Valid @RequestBody Project project) {
        return this.projectRepository.findById(id)
            .map(p -> {
                p.setTitle(post.getTitle());
                p.setContent(post.getContent());

                return p;
            })
            .flatMap(p -> this.posts.save(p));
    }
    */
	
	
	
	@DeleteMapping("/delete/{id}")
	//@DeleteMapping(path ="/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Mono<Void> delete( @PathVariable("id") Integer id ) {		
		return this.projectRepository.deleteById(id);
		//return this.projectRepository.findAll();
	}
	
	
	

}