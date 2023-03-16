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
package com.iiit.spring.sample.mongodb.domain;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "iiit_projects")
public class Project {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";

	public Integer id;
	public String name;
	public String description;

	public Project() {
	}
	
	public Project(Integer id, String name) {
		this.id = id;
		this.name = name;		
	}

	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Project(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

}
