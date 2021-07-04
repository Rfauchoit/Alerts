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
public class FirestationController {
	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Read - Get all firestations
	 * 
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/firestation")
	public Iterable<Firestation> getFirestations() {
		return firestationService.getFirestations();
	}

	@GetMapping("firestation/{id}")

	public Optional<FirestationDto> showFirestations(@PathVariable Long id) {
		//Je récupère une station par rapport à son id
		FirestationDto firestation = modelMapper.map(firestationService.getFirestations(id).get(),FirestationDto.class);
		
		List<Person> entityList = personService.getPersonsByAddress(firestation.getAddress());
		
		List<PersonDto> persons = entityList.stream()
		.map(source -> modelMapper.map(source, PersonDto.class))
		.collect(Collectors.toList());
		
		//Récupérer dans une liste les medical records des gens du dessus
		//List<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecords(persons);
		
		firestation.setHabitants(persons);

		return Optional.ofNullable(firestation);}
		
	
	@GetMapping("firestation/{stationNumber}")

	public Iterable<PersonDto> showFirestation(@PathVariable(name = "stationNumber") Long id) {

		Optional<Firestation> firestation = firestationService.getFirestations(id);
		//PersonUtil.ageFromBirthdate(birthdate);
		List<PersonDto> persons = new ArrayList<>();
		List<Person> entities = personService.getPersonsByAddress(firestation.get().getAddress());
		for (Person person : entities) {
			//Chercher le medical record correspondant à la variable personne et le stocker dans une variable m
			//Récupérer l'âge de cette personne
			//Si - de 18 incrémenter mineur sinon incrémenter majeur
			//créer l'objet personDTO en utilisant modelMapper et ajouter personDTO à la list persons
		}
		return persons;
	
		
	}

	@PostMapping("/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		Firestation firestationAdded = firestationService.save(firestation);

		if (firestationAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(firestationAdded.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("firestation/{id}")
	public void deleteFirestation(@PathVariable Long id) {

		firestationService.deleteFirestation(id);
	}

	@PutMapping("firestation/{id}")
	Firestation updateFirestation(@RequestBody Firestation firestation, @PathVariable Long id) {
		return firestationService.getFirestations(id).map(firestations -> {
			firestation.setId(firestation.getId());
			return firestationService.save(firestation);
		}).orElseGet(() -> {
			firestation.setId(id);
			return firestationService.save(firestation);
		});
	}
}
