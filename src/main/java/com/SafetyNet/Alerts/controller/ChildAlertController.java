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
import com.SafetyNet.Alerts.dto.PersonDto;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.FirestationService;
import com.SafetyNet.Alerts.service.MedicalRecordService;
import com.SafetyNet.Alerts.service.PersonService;
import com.SafetyNet.Alerts.util.PersonUtil;

@RestController
public class ChildAlertController {
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("childAlert/{address}")
	public Iterable<ChildDto> childAlert(@PathVariable(name = "address") String address) {
		List<ChildDto> childs = new ArrayList<ChildDto>();
		List<Person> entityList = personService.getPersonsByAddress(address);

		List<PersonDto> persons = entityList.stream().map(source -> modelMapper.map(source, PersonDto.class))
				.collect(Collectors.toList());

		// Set de l'âge des personnes
		for (PersonDto person : persons) {
			person.setMedicalRecord(
					medicalRecordService.getMedicalRecords(person.getFirstName(), person.getLastName()).get());
			person.setAge(PersonUtil.AgeFromBirthdate(person.getMedicalRecord().getBirthdate()));

			if (person.getAge() <= 18) {
				// Il faut convertir les personDto en ChildDto
				ChildDto child = modelMapper.map(person, ChildDto.class);
				child.setHabitants(persons.stream().filter(p -> p != person).collect(Collectors.toList()));
				childs.add(child);
			}

		}

		return childs;
	}
}