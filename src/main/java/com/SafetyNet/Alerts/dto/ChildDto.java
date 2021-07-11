package com.SafetyNet.Alerts.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class ChildDto {

	private String firstName;
	private String lastName;
	private int age;
	private String address;
	private List<PersonDto> habitants ;
	
	public ChildDto() {
		habitants = new ArrayList<PersonDto>();
	}
}
