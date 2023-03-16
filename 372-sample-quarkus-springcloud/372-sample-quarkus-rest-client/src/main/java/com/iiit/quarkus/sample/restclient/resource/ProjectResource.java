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
package com.iiit.quarkus.sample.restclient.resource;


import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.Entity;  
import javax.ws.rs.client.WebTarget;  
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.quarkus.eureka.client.EurekaClient;
import io.quarkus.eureka.client.loadBalancer.LoadBalanced;
import io.quarkus.eureka.client.loadBalancer.LoadBalancerType;

import com.iiit.quarkus.sample.restclient.domain.Project;

@Path("/projects")
@ApplicationScoped
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());
	
	@ConfigProperty(name = "eureka.service.name", defaultValue = "sample-quarkus-eureka-service")
	String serviceName;	
	
	
	@Inject
    @LoadBalanced(type = LoadBalancerType.ROUND_ROBIN)
    public EurekaClient eurekaClient;

	public ProjectResource() {		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() {
		LOGGER.info("list方法获取全部数据");		
		return eurekaClient.app(serviceName).path("/projects").request(MediaType.APPLICATION_JSON_TYPE)
                .get().readEntity(String.class);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)		
	public String getById( @PathParam("id") Integer id) {		
		WebTarget target = eurekaClient.app(serviceName).path("/projects/"+id);		
		return target.request(MediaType.APPLICATION_JSON_TYPE).get().readEntity(String.class);		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add( @NotNull @Valid Project project) {
		System.out.println("****增加Project****");  
		WebTarget target = eurekaClient.app(serviceName).path("/projects/");
		return  target.request().post(Entity.entity(project, MediaType.APPLICATION_JSON));                
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response delete ( @PathParam("id") Integer id) {
		System.out.println("****删除Project****");  
		WebTarget target = eurekaClient.app(serviceName).path("/projects/"+id);
		return  target.request().delete();             
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Parameter(required = true, description = "Project to add") @NotNull @Valid Project project) {
		System.out.println("****修改Project****");  
		WebTarget target = eurekaClient.app(serviceName).path("/projects/");
		return  target.request().put(Entity.entity(project, MediaType.APPLICATION_JSON));          
	}
	

}