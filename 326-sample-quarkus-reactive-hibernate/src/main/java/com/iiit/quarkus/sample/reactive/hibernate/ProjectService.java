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

import java.util.List;
import java.util.function.Function;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.hibernate.reactive.mutiny.Mutiny;

import org.jboss.logging.Logger;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ProjectService {

	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class
			.getName());

	@Inject
	Mutiny.Session mutinySession;

	// 获取所有Project列表
	public Uni<List<Project>> get() {
		
		return mutinySession.createNamedQuery("Projects.findAll", Project.class)
				.getResults().collectItems().asList();

	}

	// 获取单个Project
	public Uni<Project> getSingle(Integer id) {			
		return mutinySession.find(Project.class, id);
	}

	// 带事务提交增加一条记录
	public Uni<Response> add(Project project) {

		if (project == null || project.getId() == null) {
			throw new WebApplicationException(
					"Id was invalidly set on request.", 422);
		}
		return mutinySession.persist(project).chain(mutinySession::flush)
				.map(ignore -> Response.ok(project).status(201).build());
	}

	// 带事务提交修改一条记录
	public Uni<Response> update(Integer id, Project project) {
		if (project == null || project.getName() == null) {
			throw new WebApplicationException(
					"Fruit name was not set on request.", 422);
		}

		// Update function (never returns null)
		Function<Project, Uni<? extends Response>> update = entity -> {
			entity.setName(project.getName());
			return mutinySession.flush().onItem()
					.transform(ignore -> Response.ok(entity).build());
		};

		return mutinySession.find(Project.class, id)
				// If entity exists then
				.onItem().ifNotNull().transformToUni(update)
				// else
				.onItem().ifNull()
				.continueWith(Response.ok().status(404).build());

	}

	// 带事务提交删除一条记录
	public Uni<Response> delete(Integer id) {
		// Delete function (never returns null)
		Function<Project, Uni<? extends Response>> delete = entity -> mutinySession
				.remove(entity).chain(mutinySession::flush).onItem()
				.transform(ignore -> Response.ok().status(204).build());

		return mutinySession.find(Project.class, id)
				// If entity exists then
				.onItem().ifNotNull().transformToUni(delete)
				// else
				.onItem().ifNull()
				.continueWith(Response.ok().status(404).build());
	}

}
