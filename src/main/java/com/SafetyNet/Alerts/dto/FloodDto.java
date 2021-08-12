package com.SafetyNet.Alerts.dto;

import lombok.Data;
@Data
public class FloodDto {
	private String firstName;
	private String lastName;
	private Integer station;
	private String phone;
	private String[] medications;
	private String[] allergies;
	private int age;
	private String address;
}
