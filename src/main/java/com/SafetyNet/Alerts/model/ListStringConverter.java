package com.SafetyNet.Alerts.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;


public class ListStringConverter implements AttributeConverter<List<String>, String> {
	private static final String SEPARATOR = "\\|";

	@Override
	public String convertToDatabaseColumn(List<String> list) {
		  StringBuilder sb = new StringBuilder();
		  sb.append(list).append(SEPARATOR);
		  return sb.toString();}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return Arrays.asList(dbData.split(SEPARATOR));
	}
	
	}
