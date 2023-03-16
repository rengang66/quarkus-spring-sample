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
package com.iiit.quarkus.sample.reactive.panache.resource;


import java.util.List;

import javax.inject.Inject;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.acme.domain.Fruit;
import org.acme.repository.FruitRepository;
import org.jboss.logging.Logger;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import com.iiit.quarkus.sample.reactive.panache.repository.ProjectRepository;
import com.iiit.quarkus.sample.reactive.panache.domain.Project;;

@Path("projects")
@ApplicationScoped
//@Produces("application/json")
//@Consumes("application/json")
public class ProjectResource {

    private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());   

    @Inject
    ProjectRepository projectRepository;   

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<List<Project>> getAll() {
		return this.projectRepository.listAll();
	}

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> getProject(@PathParam("name") String name) {
		return this.projectRepository.findByName(name)
			.onItem().ifNotNull().transform(project -> Response.ok(project).build())
			.onItem().ifNull().continueWith(() -> Response.status(Status.NOT_FOUND).build());
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@ReactiveTransactional	
	public Uni<Void> add(@Valid Project project) {		
		return this.projectRepository.persist(project);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@ReactiveTransactional	
	public Uni<Void> update(@Valid Project project) {		
		return this.projectRepository.persistAndFlush(project);
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@ReactiveTransactional	
	public Uni<Void> delete(@Valid Project project) {		
		return this.projectRepository.delete(project);
	}
	    
}
