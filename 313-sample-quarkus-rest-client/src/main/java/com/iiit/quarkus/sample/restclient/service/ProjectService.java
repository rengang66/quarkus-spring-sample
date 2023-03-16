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
package com.iiit.quarkus.sample.restclient.service;

import java.util.Set;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.iiit.quarkus.sample.restclient.domain.Project;

@RegisterRestClient
@ApplicationScoped
public interface ProjectService {	
	
	@GET   
    @Produces("application/json")
	public Set<Project> list() ;
	
	@GET
    @Path("/{id}")
    @Produces("application/json")	
	public Project getById(@PathParam Integer id); 		
	
	@Produces("application/json")	
	@Consumes("application/json")
	public Set<Project> add( @NotNull @Valid Project project) ;

	@Produces("application/json")
	@Consumes("application/json")
	public Set<Project> update(@NotNull @Valid Project project) ;

	@Produces("application/json")
	@Consumes("application/json")
	public Set<Project> delete( @NotNull @Valid Project project);

}