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
package com.iiit.quarkus.sample.consul.mybatis.resource;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.iiit.quarkus.sample.consul.mybatis.domain.Project;
import com.iiit.quarkus.sample.consul.mybatis.service.ProjectService;

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
    public List<Project> getAll() {
        return service.getAll();
    }

    //获取单个Project信息
    @GET
    @Path("{id}")
    public Project getSingle(@PathParam("id")  Long id) {    	
    	return service.getById(id);
    }

    //增加一个Project对象
    @POST   
    public Response add( Project project) {    	
        service.add(project) ;
        return Response.ok(project).status(201).build();
     }

    //修改一个Project对象
    @PUT
    @Path("{id}")   
    public Project update(Project project) {    	
    	return service.update(project);
    }   

    //删除一个Project对象
    @DELETE
    @Path("{id}") 
    public Response delete(@PathParam("id") Long id) {    	
    	service.delete(id);
    	return Response.status(204).build();
    }

    
}
