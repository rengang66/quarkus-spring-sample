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
package com.iiit.quarkus.sample.springboot.service;


import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iiit.quarkus.sample.springboot.repository.ProjectRepository;
import com.iiit.quarkus.sample.springboot.domain.Project;

@Service
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getName());	
    
    @Autowired
	ProjectRepository projectRepository;	
	
    public Iterable<Project> list() {
        return projectRepository.findAll();
    }	
	
    public Project getById( Long id) {
		Optional<Project> optional = projectRepository.findById(id);
		Project project = null;
		if (optional.isPresent()) {
            project = optional.get();            
        }
        return project;
    }
   
    public Project add( Project project) { 
    	Optional<Project> optional = projectRepository.findById(project.getId());
    	if (!optional.isPresent()) {
    		 return projectRepository.save(project);
    	}    	
    	throw new IllegalArgumentException("Project with id " + project.getId()+ " exists");
    }
    
    public Project update( Project project) {
        Optional<Project> optional = projectRepository.findById(project.getId());
        if (optional.isPresent()) {            
            return projectRepository.save(project);
        }
        throw new IllegalArgumentException("No Project with id " + project.getId()+ " exists");
    }    
    
    public void delete( long id) {
    	projectRepository.deleteById(id);
    }
}