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
package com.iiit.quarkus.sample.rest.resource;


import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import org.apache.dubbo.config.annotation.DubboReference;
import org.jboss.logging.Logger;
import com.iiit.quarkus.sample.rest.service.ProjectService;
import com.iiit.quarkus.sample.rest.service.Project;

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

	// 注入ProjectService对象
	//@Inject
	//ProjectService service;
	
	@DubboReference(check = false)
	ProjectService projectService;

	public ProjectResource() {		
		
	}
	
	
	@GET	
	public Set<Project> list() {
		LOGGER.info("list方法获取全部数据");
		return projectService.list();
	}

	@GET
	@Path("/{id}")	
	public Project getById(@PathParam("id") Integer id) {	
		return projectService.getById(id);
	}
		

	@POST	
	public Set<Project> add(@NotNull @Valid Project project) {
		return projectService.add(project);
	}

	@PUT	
	public Set<Project> update( @NotNull @Valid Project project) {
		return projectService.update(project);
	}

	@DELETE
	@Path("/{id}")	
	public Set<Project> delete( @PathParam("id") Integer id) {
		Project project =  projectService.getById(id);
		return projectService.delete(project);
	}
	
	/*
	@GET
	@Path("/error")	
	public void doSomethingGeneratingError() {
		this.service.performWorkGeneratingError();
	}
	*/

}