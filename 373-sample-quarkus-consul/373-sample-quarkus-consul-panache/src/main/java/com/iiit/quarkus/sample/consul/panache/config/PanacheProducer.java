package com.iiit.quarkus.sample.consul.panache.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.orbitz.consul.Consul;

@ApplicationScoped
public class PanacheProducer {

	@Produces
	Consul consulClient = Consul.builder().build();

}
