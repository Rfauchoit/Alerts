package com.SafetyNet.Alerts.dto;

import com.SafetyNet.Alerts.model.MedicalRecord;

import lombok.Data;

@Data
public class PersonDto {
	
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private MedicalRecord medicalRecord;
	private int age;
	private String email;
		
	}
	
