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
package com.iiit.quarkus.sample.reactive.hibernate;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
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

    //注入服务类
    @Inject
    ProjectService service;

    //获取Project列表
    @GET
    public Uni<List<Project>> get() {
        return service.get();
    }

    //获取单个Project信息
    @GET
    @Path("{id}")
    public Uni<Project> getSingle(@PathParam("id")  Integer id) {    	
    	return service.getSingle(id);
    }
    
    @GET
    @Path("/combine/{id}")
    public Uni<Combine> getCombine(@PathParam("id")  Integer id) {    
    	Uni<List<Project>>  projects = get();
    	projects.await().indefinitely();
    	Uni<Project> project = getSingle(id);
    	project.await().indefinitely();
    	Uni<String> uni = Uni.createFrom().item("hello");
    	List<Uni<?>> unis = new ArrayList<>();     	
    	unis.add(projects);
    	unis.add(project);    	
    	unis.add(uni);  
    	
    	return Uni.combine().all().unis(unis)
                .combinedWith(results -> {
              	  Combine combine = new Combine(); 
                    for (Object result: results){
                  	  if(result instanceof List)
                      	  combine.projects = (List<Project>) result;
                  	  if(result instanceof Project)
                      	  combine.project = (Project) result;
                  	  if(result instanceof String)
                      	  combine.str_text = (String) result;
                  	  if(result instanceof Long)
                      	  combine.count = (Long) result;
                    }                      
                    return combine;
                }); 
    }
    

    //增加一个Project对象
    @POST   
    public Uni<Response> add( Project project) {
    	if (project == null || project.getId() == null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
    	 return  service.add(project) ;        
     }

    //修改一个Project对象
    @PUT
    @Path("{id}")   
    public Uni<Response> update(@PathParam("id") Integer id,Project project) {  
    	 if (project == null || project.getName() == null) {
             throw new WebApplicationException("Project name was not set on request.", 422);
         }
    	return service.update(id,project);
    }   

    //删除一个Project对象
    @DELETE
    @Path("{id}") 
    public Uni<Response> delete(@PathParam("id") Integer id) {    	
    	return service.delete(id);    	
    }

    // 处理Response的错误情况
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
