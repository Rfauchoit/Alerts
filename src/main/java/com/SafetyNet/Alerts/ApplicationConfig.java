package com.SafetyNet.Alerts;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
public class ApplicationConfig {
	   @Bean
	   public ModelMapper modelMapper() {
	      ModelMapper modelMapper = new ModelMapper();
	      modelMapper.getConfiguration()
	      	.setFieldMatchingEnabled(true)
	      	.setFieldAccessLevel(AccessLevel.PRIVATE);
	      return modelMapper;
	      
	   }
	   @Bean
	   public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
	       return new Jackson2ObjectMapperBuilder()
	         .serializationInclusion(JsonInclude.Include.NON_NULL);
}
	   }