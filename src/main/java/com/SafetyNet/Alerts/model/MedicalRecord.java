package com.SafetyNet.Alerts.model;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "medicalRecords")

public class MedicalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String birthdate;
	@Convert(converter = ListStringConverter.class)
	private List<String> medications;
	@Convert(converter = ListStringConverter.class)
	private List<String> allergies;
	@Convert(converter = StringIntegerConverter.class)
	private int age;

	public MedicalRecord() {
	}
}
