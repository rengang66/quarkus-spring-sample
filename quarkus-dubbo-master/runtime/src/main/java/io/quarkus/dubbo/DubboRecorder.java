package io.quarkus.dubbo;


import java.util.Set;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;

import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.annotation.DubboService;
import org.jboss.logging.Logger;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class DubboRecorder {
	
	static final Logger log = Logger.getLogger(DubboRecorder.class);	
	
	public void exportStart( DubboConfig dubboConfig ) {
        Set<Bean<?>> beans = CDI.current().getBeanManager().getBeans(Object.class, new AnnotationLiteral<Any>() {});
        for (Bean<?> bean : beans) {
            DubboService dubboService = bean.getBeanClass().getAnnotation(DubboService.class);
            if (dubboService != null) {
                Instance<?> instance = CDI.current().select(bean.getBeanClass());
                Class<?> interfaceClass = dubboService.interfaceClass();
                this.registerDubboService(dubboService, interfaceClass, instance.get(),dubboConfig);
                log.info("finding dubbo service =>:{}" + bean.getBeanClass());
            }
        }
    }	
	
	
	private void registerDubboService(DubboService dubboService, Class clz, Object obj,DubboConfig dubboConfig) {
        ApplicationConfig applicationConfig = DubboConfigUtils.applicationConfig(dubboConfig);
        RegistryConfig registryConfig = DubboConfigUtils.registryConfig(dubboConfig);
        ProtocolConfig protocolConfig = DubboConfigUtils.protocolConfig(dubboConfig);

        ServiceConfig<Object> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);

        this.bindParams(service, dubboService);
        service.setInterface(clz);
        service.setRef(obj);
        service.export();
    }
	
	
	private void bindParams(ServiceConfig<Object> target, DubboService orign) {
        target.setRetries(orign.retries());
        target.setGroup(orign.group());
        if (orign.timeout() != 0) {
            target.setTimeout(orign.timeout());
        }
        if (StringUtils.isNotEmpty(orign.onconnect())) {
            target.setOnconnect(orign.onconnect());
        }
        if (StringUtils.isNotEmpty(orign.group())) {
            target.setGroup(orign.group());
        }
    }
}
