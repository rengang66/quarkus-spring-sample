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
package com.iiit.spring.sample.event.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.iiit.spring.sample.event.domain.Project;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import reactor.core.publisher.Mono;


@MessageEndpoint
public class ProjectService {	
	
	private Set<Project> projects = Collections.newSetFromMap(Collections
			.synchronizedMap(new LinkedHashMap<>()));

	public ProjectService() {
		System.out.println("================ Project数据初始化! ================");
		projects.add(new Project(1, "项目A", "关于项目A的情况描述"));
		projects.add(new Project(2, "项目B", "关于项目B的情况描述"));		
	}

	@ServiceActivator(inputChannel = "list")
	public Set<Project> list() {
		return projects;
	}

	@ServiceActivator(inputChannel = "getById")
	public Project getById (Integer id) {		
		for (Project value : projects) {			
			if ( (id.intValue()) == (value.id.intValue())) {				
				return value;
			}
		}
		return null;
	}

	@ServiceActivator(inputChannel = "add")
	public Project add(Project project) {
		projects.add(project);
		return project;
	}

	@ServiceActivator(inputChannel = "add1")
	public Project add(String project) {		
		return (new Project(3, "项目B3", project));
	}
	
	@ServiceActivator(inputChannel = "update")
	public Set<Project> update(Project project) {		
		projects.removeIf(existingProject -> existingProject.name
				.contentEquals(project.name));
		projects.add(project);
		return projects;
	}

	@ServiceActivator(inputChannel = "delete")
	public Set<Project> delete(Project project) {
		projects.removeIf(existingProject -> existingProject.name
				.contentEquals(project.name));
		return projects;
	}
	
	@ServiceActivator(inputChannel = "greeting", async = "true")
	public Mono<String> consume(Mono<String> name) {
		return name.map(String::toUpperCase);
	}
	

}