package com.SafetyNet.Alerts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.Alerts.dto.FloodDto;
import com.SafetyNet.Alerts.model.Firestation;
import com.SafetyNet.Alerts.model.MedicalRecord;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.FirestationService;
import com.SafetyNet.Alerts.service.MedicalRecordService;
import com.SafetyNet.Alerts.service.PersonService;
import com.SafetyNet.Alerts.util.PersonUtil;

@RestController
public class FloodController {
	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("flood/{station}")
	public Map<String, List<FloodDto>> flood(@PathVariable(name = "station") Integer station) {
	
		
		List<Firestation> addresses = firestationService.getFirestationByStationNumber(station);
		
		Map<String, List<FloodDto>> floods = new HashMap<>();
		for (Firestation address : addresses) {
			String clef = address.getAddress();
			List<FloodDto> value = new ArrayList<>();
			List<Person> persons = personService.getPersonsByAddress(clef);
			
			for (Person person : persons) {

				MedicalRecord medicalRecord = medicalRecordService.getMedicalRecords(person.getFirstName(), person.getLastName()).get();
				FloodDto flood = new FloodDto();
				flood = modelMapper.map(medicalRecord, FloodDto.class);
				flood.setAge(PersonUtil.AgeFromBirthdate(medicalRecord.getBirthdate()));
				flood.setPhone(person.getPhone());
				value.add(flood);		
			}
			floods.put(clef, value);
		}

		return floods;
		}
	}
			