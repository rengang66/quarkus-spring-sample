package com.iiit.quarkus.sample.consul.mybatis.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.orbitz.consul.Consul;

@ApplicationScoped
public class MyBatisProducer {

	@Produces
	Consul consulClient = Consul.builder().build();

}
