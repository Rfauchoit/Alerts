package com.SafetyNet.Alerts.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SafetyNet.Alerts.dto.ChildDto;
import com.SafetyNet.Alerts.dto.FirestationDto;
import com.SafetyNet.Alerts.dto.PersonDto;
import com.SafetyNet.Alerts.model.Firestation;
import com.SafetyNet.Alerts.model.MedicalRecord;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.FirestationService;
import com.SafetyNet.Alerts.service.MedicalRecordService;
import com.SafetyNet.Alerts.service.PersonService;
import com.SafetyNet.Alerts.util.PersonUtil;

@RestController
public class PhoneAlertController {
	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("phoneAlert/{firestation_number}")
	public Iterable<String> phoneAlert(@PathVariable(name = "firestation_number") Long id) {
		//Récupérer l'adresse de la FireStation par rapport à son ID 
		Firestation firestation = firestationService.getFirestations(id).get();
		
		// Récupérer la liste des personnes associés à cette adresse
		List<Person> persons = personService.getPersonsByAddress(firestation.getAddress());

		//Convervir la liste des personnes en list<string> ne contenant que les numéros de tel
		//Créer une list 
		//Faire une boucle sur les personnes et ajouter le phone à la liste
		List<String>phoneList = new ArrayList<String>();
		
		for (Person person : persons) {
			phoneList.add(person.getPhone());
		}
	
		return phoneList;
		
	}
}
