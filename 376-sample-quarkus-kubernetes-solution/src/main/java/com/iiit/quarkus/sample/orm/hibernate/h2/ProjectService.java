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
package com.iiit.quarkus.sample.orm.hibernate.h2;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import org.jboss.logging.Logger;



@ApplicationScoped
public class ProjectService {

    private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

    //注入持久类
    @Inject
    EntityManager entityManager;
   
    //获取所有Project列表
    public List<Project> get() {
        return entityManager.createNamedQuery("Projects.findAll", Project.class)
                .getResultList();
    }
     
    //获取单个Project
    public Project getById(Integer id) { 
        Project entity = entityManager.find(Project.class, id);
        if (entity == null) {
        	String info  = "project with id of " + id + " does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }
        return entity;
    }
  
    //带事务提交增加一条记录
    @Transactional
    public Project add(Project project) {
        if (project.getId() == null) {
        	String info  = "Id was invalidly set on request.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 422);
        }
        entityManager.persist(project);
        return project;
    }  
  
    //带事务提交修改一条记录
    @Transactional
    public Project update(Project project) {
        if (project.getName() == null) {
        	String info  = "project Name was not set on request.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 422);
        }

        Project entity = entityManager.find(Project.class, project.getId());
        if (entity == null) {
        	String info  = "project with id  does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }
        entity.setName(project.getName());
        return entity;
    }

    //带事务提交删除一条记录
    @Transactional
    public void delete( Integer id) {
    	Project entity = entityManager.getReference(Project.class, id);
        if (entity == null) {
        	String info  = "project with id of " + id + " does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }
        entityManager.remove(entity);
        return ;
    }
   
}
