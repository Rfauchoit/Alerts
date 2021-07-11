package com.SafetyNet.Alerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class FirestationDto {
	
	private Long id;
	private String address;
	private Integer station;
	private List<PersonDto> habitants;
	private int nbAdults;
	private int nbChilds;
	private String phone;
	
}
