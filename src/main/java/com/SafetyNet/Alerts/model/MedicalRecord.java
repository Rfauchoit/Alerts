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

	//@Convert(converter = StringIntegerConverter.class)
	private int age;
	
	
	
	


	public String getBirthdate() {
		return birthdate;
	}



	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}



	public List<String> getMedications() {
		return medications;
	}



	public void setMedications(List<String> medications) {
		this.medications = medications;
	}



	public List<String> getAllergies() {
		return allergies;
	}



	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public MedicalRecord() {
	}
}
/*LocalDate birthdate = new LocalDate (1970, 1, 20);
LocalDate now = new LocalDate();
Years age = Years.yearsBetween(birthdate, now);*/