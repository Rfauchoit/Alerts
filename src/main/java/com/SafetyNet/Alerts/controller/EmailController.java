package com.SafetyNet.Alerts.controller;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.PersonService;

@RestController
public class EmailController {
	@Autowired
	private PersonService personService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("communityEmail/{city}")
	public Iterable<String> communityEmail(@PathVariable(name = "city") String city) {
		List<Person> persons = personService.getPersonByCity(city);

		List<String>EmailList = new ArrayList<>();
		
		for (Person person : persons) {
			EmailList.add(person.getEmail());
		}
	
		return EmailList;}}
	