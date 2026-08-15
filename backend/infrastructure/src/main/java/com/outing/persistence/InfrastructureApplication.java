package com.outing.persistence;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class InfrastructureApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(InfrastructureApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}

}
