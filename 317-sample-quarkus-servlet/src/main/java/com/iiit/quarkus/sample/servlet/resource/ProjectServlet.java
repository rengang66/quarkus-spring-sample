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
package com.iiit.quarkus.sample.servlet.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.iiit.quarkus.sample.servlet.service.ProjectService;
import com.iiit.quarkus.sample.servlet.domain.Project;

@ApplicationScoped
public class ProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProjectServlet.class
			.getName());

	@Inject
	ProjectService projectService;

	public ProjectServlet() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");

		if (id == null) {
			pw.println(projectService.getProjectInform());
		} else {
			int intID = Integer.parseInt(id);
			pw.println(projectService.getSingleProjectInform(intID));
		}

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			projectService.add(bulidProject(request));
			pw.println(projectService.getProjectInform());
		} else if ("update".equals(action)) {
			projectService.update(bulidProject(request));
			pw.println(projectService.getProjectInform());
		} else if ("delete".equals(action)) {
			projectService.delete(bulidProject(request));
			pw.println(projectService.getProjectInform());
		} else {
			pw.println("No action to do.");
		}

	}

	private Project bulidProject(HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		return new Project(id, name, description);
	}
}