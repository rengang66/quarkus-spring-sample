package com.iiit.boot.project.autoconfigure;

import com.iiit.spring.sample.service.aop.impl.CountableAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class description
 *
 */
@Configuration
@ConditionalOnProperty(name = "logging.api.enabled", havingValue = "true", matchIfMissing = true)
public class MethodCallCounterAutoConfigure {

    private final LoggingProperties properties;

    public MethodCallCounterAutoConfigure(LoggingProperties properties) {
        this.properties = properties;
    }

    @Bean
    public CountableAspect getCountableAspect(){
        return  new CountableAspect(properties.getLoggerName());
    }
}
