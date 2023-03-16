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
package com.iiit.spring.sample.rest.controller;

import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.iiit.spring.sample.rest.service.ProjectService;
import com.iiit.spring.sample.rest.domain.Project;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProjectService projectService;

	@Test
	public void getProjectFound() throws Exception {
		Mockito.when(this.projectService.getById(Mockito.eq(1))).thenReturn(
				new Project("Apple", "Winter fruit"));

		this.mockMvc
				.perform(get("/projects/1"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentTypeCompatibleWith(
								MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("name").value("Apple"))
				.andExpect(jsonPath("description").value("Winter fruit"));

		Mockito.verify(this.projectService).getById(Mockito.eq(1));
		Mockito.verifyNoMoreInteractions(this.projectService);
	}

	@Test
	public void getFruitNotFound() throws Exception {
		Mockito.when(this.projectService.getById(Mockito.eq(5))).thenReturn(
				null);

		this.mockMvc.perform(get("/projects/5"))
				.andExpect(status().isNotFound())
				.andExpect(content().string(""));

		Mockito.verify(this.projectService).getById(Mockito.eq(5));
		Mockito.verifyNoMoreInteractions(this.projectService);
	}

}