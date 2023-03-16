package com.iiit.quarkus.sample.extension.project;

import javax.inject.Inject;

public class ProjectConfigService {	
   
	ProjectConfigService(){}	
	
	@Inject
	ProjectConfig projectConfig;	
	
	public String getProjectConfig() {		
		String projectInform = "项目名称：" + projectConfig.name.get() + ";" + "项目地址：" + projectConfig.address.get();
		return projectInform;
	}
	
	public String getProjectManager() {			
		String projectInform = "项目经理：" + projectConfig.manager.managerName + ";" + "项目职位：" + projectConfig.manager.post;
		return projectInform;
	}   
    
}