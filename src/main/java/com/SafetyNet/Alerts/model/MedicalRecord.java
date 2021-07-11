package com.SafetyNet.Alerts.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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

public class MedicalRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String birthdate;
	@Convert(converter = ListStringConverter.class)
	private List<String>medications;
	@Convert(converter = ListStringConverter.class)
	private List<String> allergies;

	public MedicalRecord() {
	}
}