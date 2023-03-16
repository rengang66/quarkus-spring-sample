package com.iiit.quarkus.sample.extension.project;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.Optional;


@ConfigRoot(name = "project",phase = ConfigPhase.RUN_TIME)
public class ProjectConfig {
   
	/**
     * Project name
     */   
    @ConfigItem(defaultValue = "rengang",name = "name")
    public Optional<String> name;
    //public String name;
    
    /**
     * Project address
     */
    @ConfigItem(defaultValue = "china",name = "address")
    public Optional<String> address;
    //public String address;

    /**
     * Project address
     */
    public Manager manager;   
    
    /**
     * Project address
     */
    @ConfigGroup
    public static class Manager {
      
    	 /**
         * Project managerName
         */
        @ConfigItem(defaultValue = "roger",name = "managerName")
        public String managerName;

        /**
         * Project manger post
         */
        @ConfigItem(defaultValue = "Manager",name = "post")
        public String post;

    }
    
    
}