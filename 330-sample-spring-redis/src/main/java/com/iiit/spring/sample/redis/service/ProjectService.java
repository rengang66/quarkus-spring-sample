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
package com.iiit.spring.sample.redis.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.iiit.spring.sample.redis.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.jboss.logging.Logger;

@Service
public class ProjectService {	
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getName());
	
	private Set<Project> projects = Collections.newSetFromMap(Collections
			.synchronizedMap(new LinkedHashMap<>()));	
		
	@Autowired
    private RedisTemplate<String, Project> redisTemplate;

	public ProjectService() {}

	public Set<Project> list() {		
		//System.out.println("初始化数据");
		this.add(new Project(1, "项目A", "关于项目A的情况描述"));
		this.add(new Project(2, "项目B", "关于项目B的情况描述"));
		LOGGER.info("获取数据");
		projects.add(getById(1));
		projects.add(getById(2));
		//this.add(projects[1]);
		return projects;
	}

	public Project getById(Integer id) {		
		//System.out.println("getById数据1");		
		Project project = redisTemplate.opsForValue().get(String.valueOf(id));
		System.out.println("getById数据2");
		if (project == null) System.out.println(" get is null ");
		return project;
	}

	public Project add( Project project) {
		//System.out.println("数据add");
		/*
		if (redisTemplate.hasKey(project.id)){
			redisTemplate.delete(project.id);
		}
		redisTemplate.opsForValue().set(project.id, project);
		*/
		
		System.out.println(String.valueOf(project.id));
		if (redisTemplate.hasKey(String.valueOf(project.id))){
			return null;
		}
		redisTemplate.opsForValue().set(String.valueOf(project.id), project);		
		return project;
	}

	public Project update( Project project) {		
		if (redisTemplate.hasKey(String.valueOf(project.id))){
			redisTemplate.delete(String.valueOf(project.id));
		}
		redisTemplate.opsForValue().set(String.valueOf(project.id), project);
		return project;
	}

	public Project delete(Project project) {
		if (redisTemplate.hasKey(String.valueOf(project.id))){
			redisTemplate.delete(String.valueOf(project.id));
			System.out.println("删除一条数据");
		}
		return project;
	}

}