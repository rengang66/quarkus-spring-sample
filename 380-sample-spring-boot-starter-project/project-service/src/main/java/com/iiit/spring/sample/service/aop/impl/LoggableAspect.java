package com.iiit.spring.sample.service.aop.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect to log method execution time.
 *
 */
@Aspect
public class LoggableAspect {
    private final Logger log;

    public LoggableAspect(String loggerName){
        super();
        log = LoggerFactory.getLogger(loggerName);
    }

    @Pointcut("@annotation(com.iiit.spring.sample.service.aop.TrackTime)")
    public void executeTiming(){}

    @Around("executeTiming()")
    public Object logMethodTiming(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object returnValue = proceedingJoinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        log.info("Time Taken by service '{}' is {} ms", proceedingJoinPoint.getSignature().getName(), totalTime);
        return returnValue;
    }
}
