package com.SafetyNet.Alerts;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.SafetyNet.Alerts.model.LoadDataModel;
import com.SafetyNet.Alerts.service.FirestationService;
import com.SafetyNet.Alerts.service.MedicalRecordService;
import com.SafetyNet.Alerts.service.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class JsondbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsondbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PersonService personService, FirestationService firestationService, MedicalRecordService medicalRecordService) {
		return args -> {
			// read json and write to db
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(df);
			mapper.registerModule(new JavaTimeModule());
		    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			TypeReference<LoadDataModel> typeReference = new TypeReference<LoadDataModel>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				LoadDataModel loadDataModel = mapper.readValue(inputStream,typeReference);
				personService.save(loadDataModel.getPersons());
				firestationService.save(loadDataModel.getFirestations());
				medicalRecordService.save(loadDataModel.getMedicalrecords());
						
				System.out.println("Data saved!");
			} catch (IOException e){
				System.out.println("Unable to save persons: " + e.getMessage());
			}
		};
	}}
		