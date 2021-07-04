package com.SafetyNet.Alerts.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MedicalRecordId implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	
	public MedicalRecordId() {
		
	}
	
	public MedicalRecordId(String firstname,String lastname) {
		super();
		this.firstName = firstname;
		this.lastName = lastname;
	}
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalRecordId other = (MedicalRecordId) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}
	
	

}
