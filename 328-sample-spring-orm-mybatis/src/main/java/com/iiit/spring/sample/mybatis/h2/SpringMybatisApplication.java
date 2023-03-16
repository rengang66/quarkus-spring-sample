package com.iiit.spring.sample.mybatis.h2;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MappedTypes({Department.class,Employee.class})
@MapperScan("com.iiit.spring.sample.mybatis.h2.mapper")
@SpringBootApplication
public class SpringMybatisApplication 
{
	private static Logger logger = LoggerFactory.getLogger(SpringMybatisApplication.class);
	
    public static void main( String[] args )
    {
    	logger.info("Starting the application");
        // System.out.println( "Spring Boot Mybatis H2 Stack" );
        SpringApplication.run(SpringMybatisApplication.class, args);
        System.out.println("================ spring boot is running! ================");
    }
}
