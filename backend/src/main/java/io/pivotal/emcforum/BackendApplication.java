package io.pivotal.emcforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
public class BackendApplication{

	@Bean
	Sampler alwaysSampler(){
		return new AlwaysSampler();
	}


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}

