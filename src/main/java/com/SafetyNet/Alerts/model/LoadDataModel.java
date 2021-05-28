package com.SafetyNet.Alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class LoadDataModel {
	private List<Person> persons;
	private List<Firestation> firestations;
	private List<MedicalRecord> medicalrecords;
	
}
