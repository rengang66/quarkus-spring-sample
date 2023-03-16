package io.quarkus.dubbo;



import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
import io.quarkus.arc.deployment.ResourceAnnotationBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.GeneratedResourceBuildItem;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.jboss.jandex.DotName;


class DubboProcessor {

    private static final String FEATURE = "quarkus-dubbo";
    private static final DotName DUBBO_PROVIDER = DotName.createSimple(DubboService.class.getName());


    @BuildStep
    public void feature(BuildProducer<FeatureBuildItem> feature) {
        feature.produce(new FeatureBuildItem(FEATURE));
    }
    
    @BuildStep
    void setupResourceInjection(BuildProducer<ResourceAnnotationBuildItem> resourceAnnotations, BuildProducer<GeneratedResourceBuildItem> resources) {
        resources.produce(new GeneratedResourceBuildItem("META-INF/services/io.quarkus.arc.ResourceReferenceProvider",
                DubboReferenceProvider.class.getName().getBytes()));
        resourceAnnotations.produce(new ResourceAnnotationBuildItem(DotName.createSimple(DubboReference.class.getName())));
    }    

    @BuildStep
    BeanDefiningAnnotationBuildItem additionalBeanDefiningAnnotation() {
        return new BeanDefiningAnnotationBuildItem(DUBBO_PROVIDER);
    }    
    
    @BuildStep
    void unremovableBeans(BuildProducer<AdditionalBeanBuildItem> beanProducer) {
        beanProducer.produce(AdditionalBeanBuildItem.unremovableOf(DubboProducers.class));
    }    
    
    @Record(ExecutionTime.RUNTIME_INIT)
    @BuildStep
	void exportStart(DubboRecorder recorder,DubboConfig config) {		
		recorder.exportStart(config);
	}
    
}
