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
package com.iiit.quarkus.sample.security.authentication;

import io.quarkus.security.identity.SecurityIdentity;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


@Path("projects")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProjectResource {

    private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

    @Inject
    ProjectService service;
    
    @Inject
    SecurityIdentity keycloakSecurityContext;

    
    
    
    
    
    
    
    @GET    
    public List<Project> get() {
        return service.get();
    }

    @GET
    @Path("{id}")    
    public Project getSingle(@PathParam("id")  Integer id) {    	
    	return service.getSingle(id);
    }

    @POST   
    public Response create( Project project) {    	
        service.create(project) ;
        return Response.ok(project).status(201).build();
     }

    @PUT
    @Path("{id}")   
    public Project update(@PathParam Integer id, Project project) {    	
    	return service.update(id, project);
    }

    @DELETE
    @Path("{id}")    
    public Response delete(@PathParam("id") Integer id) {    	
    	service.delete(id);
    	return Response.status(204).build();
    }
    
    @GET
    @Path("/api/public")
    @PermitAll    
    public List<Project> publicResource() {
    	return service.get();        
    }
    
    @GET
	@RolesAllowed("admin")
    @Path("/api/admin")
	public List<Project> adminResource() {		
		return service.get();
	}
    
    @GET
    @RolesAllowed("user")
    @Path("/api/users/user")    
    public List<Project> userResource(@Context SecurityContext securityContext) {
    	System.out.println(securityContext.getUserPrincipal().getName());
        return service.get();
    }
    
    @GET
    @RolesAllowed("user")
    @Path("/api/users/reng")   
    public List<Project> rengResource(@Context SecurityContext securityContext) {    	
    	System.out.println(securityContext.getUserPrincipal().getName());
        return service.get();
    }
    
    
    
    @GET
    @Path("/api/public")
    @Produces(MediaType.APPLICATION_JSON)   
    @PermitAll
    public List<Project> serveResource() {
    	 LOGGER.info("/api/public"); 
    	 return service.get();
    }
    
    @GET
    @Path("/api/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> manageResource() {
    	 LOGGER.info("granted"); 
    	 return service.get();
    }
    
    @GET
    @Path("/api/users/user")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserResource() {
        return new User(keycloakSecurityContext);
    }
    
    public static class User {
        private final String userName;
        User(SecurityIdentity securityContext) {
            this.userName = securityContext.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    } 
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> inform() {
    	 LOGGER.info("inform is granted"); 
    	 return service.get();
    }
    
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
                    .add("exceptionType", exception.getClass().getName())
                    .add("code", code);

            if (exception.getMessage() != null) {
                entityBuilder.add("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(entityBuilder.build())
                    .build();
        }
    }
}
