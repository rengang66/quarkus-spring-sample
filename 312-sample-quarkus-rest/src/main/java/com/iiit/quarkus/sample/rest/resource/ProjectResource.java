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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import org.jboss.logging.Logger;
import com.iiit.quarkus.sample.rest.service.ProjectService;
import com.iiit.quarkus.sample.rest.domain.Project;

import javax.ws.rs.client.WebTarget;

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Project Resource", description = "Endpoints for projects")
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

	// 注入ProjectService对象
	@Inject	ProjectService service;

	public ProjectResource() {				
	}	
	
	@GET
	@Operation(summary = "Get all projects", description = "Get all projects")
	@APIResponse(responseCode = "200", description = "All projects")
	public Set<Project> list() {
		LOGGER.info("list方法获取全部数据");
		return service.list();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Get a project", description = "Get a project")
	@APIResponse(responseCode = "200", description = "Requested project", content = @Content(schema = @Schema(implementation = Project.class)))
	@APIResponse(responseCode = "404", description = "Project not found")
	//public Project getById(@PathParam("id") Integer id) {		
	public Project getById(@Parameter(required = true, description = "Project id") @PathParam("id") Integer id) {	
		return service.getById(id);
	}
	
	@POST
	@Operation(summary = "Add a new project", description = "Add a new project")
	@APIResponse(responseCode = "200", description = "Project added")	
	//public Set<Project> add(Project project) {
	public Set<Project> add(@Parameter(required = true, description = "Project to add") @NotNull @Valid Project project) {
		return service.add(project);
	}

	@PUT
	@Operation(summary = "Update a new project", description = "Update a new project")
	@APIResponse(responseCode = "200", description = "Project update")
	public Set<Project> update(@Parameter(required = true, description = "Project to update") @NotNull @Valid Project project) {
		return service.update(project);
	}

	@DELETE
	@Operation(summary = "Delete a project", description = "Delete a project")
	@APIResponse(responseCode = "204", description = "Project deleted")
	public Set<Project> delete(@Parameter(required = true, description = "Project to delete") @NotNull @Valid Project project) {
		return service.delete(project);
	}
	
	@GET
	@Path("/error")
	@Operation(summary = "Do something that will most likely generate an error", description = "Do something that will most likely generate an error")
	@APIResponse(responseCode = "204", description = "Success")
	@APIResponse(responseCode = "500", description = "Something bad happened")
	public void doSomethingGeneratingError() {
		this.service.performWorkGeneratingError();
	}
	

}