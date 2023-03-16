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
package com.iiit.spring.sample.reactive.rest.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.time.Duration;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.iiit.spring.sample.reactive.rest.domain.Project;

@Service
public class ProjectService {	
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getName());
	
	private Set<Project> projects = Collections.newSetFromMap(Collections
			.synchronizedMap(new LinkedHashMap<>()));

	public ProjectService() {
		LOGGER.info("初始化数据！");
		projects.add(new Project(1, "项目A", "关于项目A的情况描述"));
		projects.add(new Project(2, "项目B", "关于项目B的情况描述"));		
	}
	
	public Flux<Project> getFruits() {
		return Flux.fromIterable(projects);
	}
	
	public Mono<Project> getById(Integer id) {		
		for (Project value : projects) {			
			if ( (id.intValue()) == (value.id.intValue())) {				
				return Mono.justOrEmpty(value);
			}
		}
		return null;		
	}	
	
	public Flux<Project> add(Project project) {
		return Mono.fromSupplier(() -> this.projects.add(project))
			.thenMany(Flux.fromIterable(this.projects));
	}
		
	public Flux<Project> update(Project project) {
		projects.removeIf(existingProject -> existingProject.name
				.contentEquals(project.name));
		return Mono.fromSupplier(() -> this.projects.add(project))
			.thenMany(Flux.fromIterable(this.projects));
	}
		
	public Flux<Project> delete(Project project) {
		projects.removeIf(existingProject -> existingProject.name
				.contentEquals(project.name));
		return Flux.fromIterable(projects);
	}
}