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
package com.iiit.quarkus.sample.mybatis.h2.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import org.jboss.logging.Logger;

import com.iiit.quarkus.sample.mybatis.h2.domain.Project;

import com.iiit.quarkus.sample.mybatis.h2.mapper.ProjectMapper;

import org.apache.ibatis.session.SqlSessionFactory;

@ApplicationScoped
public class ProjectService {

    private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getName());

    //注入持久类
    @Inject
    ProjectMapper projectMapper;
   
    @Inject
    SqlSessionFactory factory;
    
    //获取所有Project列表
    public List<Project> getAll() {
        return projectMapper.getAllProject();
    }
     
    //获取单个Project
    public Project getById(Long id) { 
        Project entity = projectMapper.getProjectById(id);
        if (entity == null) {
        	String info  = "project with id of " + id + " does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }
        return entity;
    }
  
    //带事务提交增加一条记录
    public Project add(Project project) {
        if (project.getId() == null) {
        	String info  = "Id was invalidly set on request.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 422);
        }
        projectMapper.addProject(project);
        return project;
    }  
  
    //带事务提交修改一条记录   
    public Project update(Project project) {
        if (project.getName() == null) {
        	String info  = "project Name was not set on request.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 422);
        }

        Project entity = projectMapper.getProjectById(project.getId());
        if (entity == null) {
        	String info  = "project with id  does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }
        projectMapper.updateProject(project);
        return entity;
    }

    //带事务提交删除一条记录    
    public void delete( Long id) {    	
        projectMapper.deleteProject(id);
        return ;
    }
   
}
