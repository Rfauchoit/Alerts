package com.SafetyNet.Alerts.controller;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.Alerts.dto.PersonDto;
import com.SafetyNet.Alerts.model.MedicalRecord;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.MedicalRecordService;
import com.SafetyNet.Alerts.service.PersonService;
import com.SafetyNet.Alerts.util.PersonUtil;

@RestController
public class PersonInfoController {
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("persoInfo/{firstName,lastName}")
	public Iterable<PersonDto> Person(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

		List<Person> persons = personService.getPersonByFirstNameAndLastName(firstName,lastName);
		
		List<PersonDto> personsInfo = new ArrayList<PersonDto>();
		for (Person person : persons) {

			MedicalRecord medicalRecord = medicalRecordService.getMedicalRecords(person.getFirstName(), person.getLastName()).get();
			PersonDto personInfo = new PersonDto();
			personInfo = modelMapper.map(medicalRecord, PersonDto.class);
			personInfo.setAge(PersonUtil.AgeFromBirthdate(medicalRecord.getBirthdate()));
			personInfo.setEmail(person.getEmail());
			personInfo.setAddress(person.getAddress());
			
			personsInfo.add(personInfo);		
		}

		return personsInfo;
			
	}
}