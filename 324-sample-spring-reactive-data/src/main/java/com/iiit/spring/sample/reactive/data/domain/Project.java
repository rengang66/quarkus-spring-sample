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
package com.iiit.spring.sample.reactive.data.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotBlank;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;



@Table("iiit_projects")
public class Project implements Serializable  {

    @Id
    @Column("id")
    private Integer id;

    //@NotBlank(message = "Name is mandatory")
    @Column("name")
    private String name;

    public Project() {
	}
     
    /*
    public Project(String name) {
        this.name = name;
    }
    */
    
    public Project(Integer id,String name) {
    	this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		Project project = (Project) o;
		return this.id.equals(project.id);
	}
    
    @Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

}
