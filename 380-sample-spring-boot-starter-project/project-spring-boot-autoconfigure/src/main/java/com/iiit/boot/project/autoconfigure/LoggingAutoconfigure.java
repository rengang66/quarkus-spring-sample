package com.iiit.boot.project.autoconfigure;

import com.iiit.spring.sample.service.aop.impl.LoggableAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to conditionally load Aspect class.
 *
 */
@Configuration
@ConditionalOnClass(LoggableAspect.class)
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingAutoconfigure {

    private final LoggingProperties properties;

    public LoggingAutoconfigure(LoggingProperties properties) {
        this.properties = properties;
    }

    @Bean
    public LoggableAspect loggableAspect(){
        return new LoggableAspect(properties.getLoggerName());
    }
}
