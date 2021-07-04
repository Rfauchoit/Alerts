package com.SafetyNet.Alerts.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class PersonUtil {

	public static long ageFromBirthdate(String birthdate) {
		String pattern = "MM/dd/yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate localDate = LocalDate.parse(birthdate, formatter);
		return localDate.until(LocalDate.now(), ChronoUnit.YEARS);
				//Period.between(localDate, LocalDate.now()).getYears();
	}
	
	
}