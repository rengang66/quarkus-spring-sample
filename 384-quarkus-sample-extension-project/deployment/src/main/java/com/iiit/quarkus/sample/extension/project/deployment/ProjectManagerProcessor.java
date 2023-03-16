package com.iiit.quarkus.sample.extension.project.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.annotations.ExecutionTime;


import com.iiit.quarkus.sample.extension.project.ProjectService;
import com.iiit.quarkus.sample.extension.project.ProjectConfigService;
import com.iiit.quarkus.sample.extension.project.ProjectRecorder;


class ProjectManagerProcessor {

	private static final String FEATURE = "iiit-project-service";

	@BuildStep
	FeatureBuildItem feature() {
		return new FeatureBuildItem(FEATURE);
	}

	@BuildStep
	public AdditionalBeanBuildItem buildProject() {
		return new AdditionalBeanBuildItem(ProjectService.class);
	}
	
	@BuildStep
    void registerTestServiceBeans(BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        AdditionalBeanBuildItem additionalBeansItem = AdditionalBeanBuildItem.builder()                
                .addBeanClass(ProjectConfigService.class)
                .setRemovable()
                .build();
        additionalBeans.produce(additionalBeansItem);
    }	
	
	@BuildStep
	@Record(ExecutionTime.STATIC_INIT)	
	StartupServiceBuildItem bulidStartupService(ProjectRecorder recorder) {		
		return new StartupServiceBuildItem(recorder.getRuntimeStartupService());	
	}
	
	@Record(ExecutionTime.RUNTIME_INIT)
    @BuildStep
	void printStartup( StartupServiceBuildItem startupServiceBuildItem,
			ProjectRecorder recorder) {		
		recorder.doStartup(startupServiceBuildItem.getService());
	}
}
