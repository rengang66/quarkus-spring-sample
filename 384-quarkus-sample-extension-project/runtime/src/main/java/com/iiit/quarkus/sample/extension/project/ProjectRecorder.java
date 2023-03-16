package com.iiit.quarkus.sample.extension.project;


import org.jboss.logging.Logger;
import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class ProjectRecorder {
	
	static final Logger log = Logger.getLogger(ProjectRecorder.class);	
	
	public RuntimeValue<StartupService> getRuntimeStartupService() {
		StartupService startupService = new StartupService();
		return new RuntimeValue<>(startupService);
	}	
	
	public void doStartup( RuntimeValue<StartupService> startupService) {		
		startupService.getValue().printStartupWord();
    }
}
