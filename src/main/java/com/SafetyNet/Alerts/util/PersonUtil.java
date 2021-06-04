package com.SafetyNet.Alerts.util;

import java.time.LocalDate;
import java.time.Period;

public final class PersonUtil {

	public static int ageFromBirthdate(String birthdate) {
		LocalDate localDate = LocalDate.parse(birthdate);
		return Period.between(localDate, LocalDate.now()).getYears();
	}
}