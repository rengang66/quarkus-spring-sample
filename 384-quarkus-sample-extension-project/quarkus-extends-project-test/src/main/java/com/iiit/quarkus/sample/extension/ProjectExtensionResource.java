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
package com.iiit.quarkus.sample.extension;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import com.iiit.quarkus.sample.extension.project.Project;
import com.iiit.quarkus.sample.extension.project.ProjectConfigService;

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectExtensionResource {

	@Inject
	ProjectExtensionService service;	
	
	@Inject
	ProjectConfigService config;	
	 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return service.getHello();
    }    
    
    @GET
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/projectconfig/")
    public String getProjectConfig() {    	
    	return config.getProjectConfig();    	
    }
    
    @GET
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/projectmanager/")
    public String getProjectManager() {    	
    	return config.getProjectManager();    	
    }
    
    
    @GET
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/project/")
    public String getProject() {
    	return service.getProject();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/projects/")
    public List<Project> getAllProject() {
    	return service.getAllProject();
    }
    
    @GET
	@Path("{id}")
	public Project getById(@PathParam("id") Integer id) {		
		return service.getById(id);
	}

	@POST
	public List<Project> add(Project project) {
		return service.add(project);
	}

	@PUT
	public List<Project> update(Project project) {
		return service.update(project);
	}

	@DELETE
	public List<Project> delete(Project project) {
		return service.delete(project);
	}
	
	
    
}