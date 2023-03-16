package com.iiit.boot.project.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Property class to set LoggingProperties.
 *
 * @author : vishnu.g
 * created on : 02/Sep/2020
 */
@ConfigurationProperties("logging.method.exec")
public class LoggingProperties {
    private String loggerName = "定义的切面输出：";

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
