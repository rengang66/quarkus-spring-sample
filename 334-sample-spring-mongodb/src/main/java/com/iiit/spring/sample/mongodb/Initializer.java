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
package com.iiit.spring.sample.mongodb;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iiit.spring.sample.mongodb.repository.ProjectRepository;
import com.iiit.spring.sample.mongodb.domain.Project;
import com.iiit.spring.sample.mongodb.service.SequenceGeneratorService;

@Component
class Initializer implements CommandLineRunner {

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
		
    private final ProjectRepository repository;

    public Initializer( ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {

    	ArrayList<Project> projects = new ArrayList<Project>( Arrays.asList(
    			new Project((int)sequenceGeneratorService.generateSequence(Project.SEQUENCE_NAME),"项目A", "项目A描述"),
    			new Project((int)sequenceGeneratorService.generateSequence(Project.SEQUENCE_NAME),"项目B", "项目B描述")) );
    	 
    	System.out.println(projects);
    	
    	repository.deleteAll();
    	
        repository.saveAll(projects);

        repository.findAll().forEach(System.out::println);
    }
}