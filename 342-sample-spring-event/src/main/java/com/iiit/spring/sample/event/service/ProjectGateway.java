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

import java.util.Set;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.iiit.spring.sample.event.domain.Project;

import reactor.core.publisher.Mono;

@MessagingGateway
public interface ProjectGateway {	
	
	@Gateway(requestChannel = "list")
	Set<Project> list(String all);
	
	@Gateway(requestChannel = "getById")
	Project getProjectById(Integer id);

	@Gateway(requestChannel = "add")
	Project addProject(Project project);
	
	@Gateway(requestChannel = "add1")
	Project addProject(String project);
	
	@Gateway(requestChannel = "update")
	Set<Project> updateProject(Project project);
	
	@Gateway(requestChannel = "delete")
	Set<Project> deleteProject(Project project);
	
	@Gateway(requestChannel = "greeting")
	Mono<String> greeting(Mono<String> input);

	@Gateway(requestChannel = "blocking-greeting")
	String blockingGreeting(String input);
}
