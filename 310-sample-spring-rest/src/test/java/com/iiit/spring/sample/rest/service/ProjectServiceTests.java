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
package com.iiit.spring.sample.rest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.iiit.spring.sample.rest.domain.Project;

import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;

@Service
public class ProjectServiceTests {	
	
	ProjectService projectService = new ProjectService();

	@Test
	public void getProjects() {
		assertThat(this.projectService.list())
			.hasSize(2);
	}

	/*
	@Test
	public void getProjectFound() {
		assertThat(this.projectService.getById(1))
			.get()
			.extracting(Project::getName, Project::getDescription)
			.containsExactly("Apple", "Winter fruit");
	}

	@Test
	public void getFruitNotFound() {
		assertThat(this.projectService.getFruit("Pear"))
			.isNotPresent();
	}

	@Test
	public void addFruit() {
		assertThat(this.projectService.addFruit(new Fruit("Pear", "Delicious fruit")))
			.hasSize(3);
	}

	@Test
	public void delete() {
		this.projectService.deleteFruit("Apple");

		assertThat(this.projectService.getFruits())
			.hasSize(1);
	}
    */

}