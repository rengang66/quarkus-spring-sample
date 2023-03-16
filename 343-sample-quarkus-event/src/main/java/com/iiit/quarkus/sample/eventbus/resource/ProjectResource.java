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
package com.iiit.quarkus.sample.eventbus.resource;

import javax.enterprise.context.ApplicationScoped;
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

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;

import org.jboss.logging.Logger;

import com.iiit.quarkus.sample.eventbus.domain.Project;
import com.iiit.quarkus.sample.eventbus.service.ProjectService;


@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);		
	
	@Inject
	ProjectService service;
	
	@Inject
    EventBus bus;

	public ProjectResource() {}		
    
	@GET
	public Uni<String> list() {		
		return bus.<String> request("getAllProjectInform","All")
                .onItem().transform(Message::body);		
	}    
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Uni<String> getName(@PathParam("id") Integer id) {
        return bus.<String> request("getNameByID", id)
                .onItem().transform(Message::body);
    }    
    
    @GET   
    @Path("/project/{id}")
    public Uni<Project> getProjectById(@PathParam("id") Integer id) {
        return bus.<Project> request("getProjectById", id)
                .onItem().transform(Message::body);
    }     
   
	@POST
	public Uni<Project> add(Project project) {		
		return bus.<Project> request("addProject", project)
                .onItem().transform(Message::body);
	}
	

	@PUT
	public Uni<Project> update(Project project) {		
		return bus.<Project> request("updateProject", project)
                .onItem().transform(Message::body);
	}

	@DELETE
	public Uni<String> delete(Project project) {		
		return bus.<String> request("deleteProject", project)
                .onItem().transform(Message::body);
	}
	
	
}