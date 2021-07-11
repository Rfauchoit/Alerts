package com.SafetyNet.Alerts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.Alerts.dto.ChildDto;
import com.SafetyNet.Alerts.dto.FireDto;
import com.SafetyNet.Alerts.dto.PersonDto;
import com.SafetyNet.Alerts.model.Firestation;
import com.SafetyNet.Alerts.model.MedicalRecord;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.FirestationService;
import com.SafetyNet.Alerts.service.MedicalRecordService;
import com.SafetyNet.Alerts.service.PersonService;
import com.SafetyNet.Alerts.util.PersonUtil;

@RestController
public class FireController {
	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("fire/{address}")
	public Iterable<FireDto> fire(@PathVariable(name = "address") String address) {
	
		
		List<Person> persons = personService.getPersonsByAddress(address);
		Firestation firestation = firestationService.getFirestationByAddress(address);

		List<FireDto> fires = new ArrayList<FireDto>();
		for (Person person : persons) {

			MedicalRecord medicalRecord = medicalRecordService.getMedicalRecords(person.getFirstName(), person.getLastName()).get();
			FireDto fire = new FireDto();
			fire = modelMapper.map(medicalRecord, FireDto.class);
			fire.setAge(PersonUtil.AgeFromBirthdate(medicalRecord.getBirthdate()));
			fire.setPhone(person.getPhone());
			fire.setStation(firestation.getStation());
			fires.add(fire);		
		}

		return fires;
			
}
}
