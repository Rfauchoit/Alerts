package com.SafetyNet.Alerts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AlertsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}
	
	@Override
	    public void run(String... args) throws Exception {
		//http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	    }
}
