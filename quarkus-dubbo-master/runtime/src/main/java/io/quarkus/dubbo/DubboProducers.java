package io.quarkus.dubbo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DubboProducers {

    private Logger logger = LoggerFactory.getLogger(DubboProducers.class);
   
    private final  DubboConfig dubboConfig ;     
    
    public  DubboProducers ( DubboConfig dubboConfig ) {
        this.dubboConfig = dubboConfig;
    }    
   
    DubboConfig getDubboConfig() {
        return this.dubboConfig;
    }
   
}
