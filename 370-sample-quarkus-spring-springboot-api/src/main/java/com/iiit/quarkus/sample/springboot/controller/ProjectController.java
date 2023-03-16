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
package com.iiit.quarkus.sample.springboot.controller;

import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.iiit.quarkus.sample.springboot.service.ProjectService;
import com.iiit.quarkus.sample.springboot.domain.Project;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectController.class);
	   
    @Autowired
    ProjectService projectService;	
	
	@Secured("admin")
	@GetMapping()	
	public Iterable<Project> list() {
		LOGGER.info("获取所有数据");
		return projectService.list();
	}	
	
	@Secured("user")
	@GetMapping("/{id}")    
	public Response getById(@PathVariable(name = "id")  Long id) {        	
        Project project = projectService.getById(id);        
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(project).build();		
	}
    
	@Secured("user")
	@RequestMapping("/add")	
	public Response add(@RequestBody Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		projectService.add(project);
		return Response.ok(project).build();
	}

	@Secured("user")
	@RequestMapping("/update")	
	public Response update(@RequestBody Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		projectService.update(project);
		return Response.ok(project).build();
	}

	@Secured("user")
	@RequestMapping("/delete")	
	public Response delete(@RequestBody Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		projectService.delete(project.getId());
		return Response.ok(project).build();
	}
}