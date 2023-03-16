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
package com.iiit.quarkus.sample.eventbus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import org.jboss.logging.Logger;

import com.iiit.quarkus.sample.eventbus.domain.Project;

@ApplicationScoped
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	private Map<Integer, Project> projectMap = new HashMap<>();
	
	public ProjectService() {	
		LOGGER.info("数据初始化！");
		projectMap.put(1, new Project(1, "项目A", "关于项目A的情况描述"));
		projectMap.put(2, new Project(2, "项目B", "关于项目B的情况描述"));
		projectMap.put(3, new Project(3, "项目C", "关于项目C的情况描述"));       
	}	
	
	
    //@ConsumeEvent("getProjectList")
	public Uni<List<Project>> getProjectList(String ls) {    	
		LOGGER.info("Multi形成List列表");    	
		return Uni.createFrom().multi(Multi.createFrom().items(new ArrayList<>(projectMap.values())));				
    }
	
	@ConsumeEvent("getAllProjectInform")
	public String getProjectInform(String ls) {
		String projectContent ="";
		for (Project value : projectMap.values()) {
			String projectInform = "{项目名称：" + value.name + "，" + "项目描述："
					+ value.description + "},";
			projectContent = projectContent + projectInform;
		}
		return projectContent;
	}
    
	
	@ConsumeEvent("getProjectById")
	public Uni<Project> getProject(Integer id) {
		LOGGER.info("Uni形成Project对象");
		Project project = projectMap.get(id);
        return Uni.createFrom().item(project);
    }
	
	@ConsumeEvent("getNameByID")
    public String getName(Integer id) {    	    	
		String projectInform = "";		
		if (projectMap.containsKey(id))	{			
			Project project = projectMap.get(id);
			projectInform = "{项目名称：" + project.name ;	
		}	
		return projectInform;
    }	
	
	
	@ConsumeEvent("addProject")
	public Uni<Project> add ( Project project) {		
		projectMap.put(projectMap.size()+1,project);
		return Uni.createFrom().item(project);			
	}
	
	
	
	@ConsumeEvent("updateProject")
	public Uni<Project> update(Project project) {		
		if (projectMap.containsKey(project.id))	{
			projectMap.replace(project.id, project);			
		}		
		return Uni.createFrom().item(project);
	}

	@ConsumeEvent("deleteProject")
	public Uni<String> delete(Project project) {
		String isRemove = "No Project is Removed";
		if (projectMap.containsKey(project.id))	{
			projectMap.remove(project.id);	
			isRemove = "Project is Removed";
		}
		return Uni.createFrom().item(isRemove);
	}
	
	public String getProjectByName(String name) {
		String projectContent ="";
		for (Project value : projectMap.values()) {
			if ( value.name ==name ){				
				String projectInform = "{项目名称：" + value.name + "，" + "项目描述："
						+ value.description + "},";
				projectContent = projectContent + projectInform;
			}			
		}
		return projectContent;
	}
	
	public String getProjectInformById(Integer id){
		String projectInform = "";		
		if (projectMap.containsKey(id))	{			
			Project project = projectMap.get(id);
			projectInform = "{项目名称：" + project.name + "，" + "项目描述："
					+ project.description + "}";	
		}	
		return projectInform;
	}

}