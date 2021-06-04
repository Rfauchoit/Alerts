package com.SafetyNet.Alerts.controller;

import java.net.URI;
import java.util.Optional;

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

import com.SafetyNet.Alerts.model.Firestation;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.FirestationService;
import com.SafetyNet.Alerts.service.PersonService;
import com.SafetyNet.Alerts.util.PersonUtil;

@RestController
public class FirestationController {
	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	/**
	 * Read - Get all firestations
	 * 
	 * @return - An Iterable object of Employee full filled
	 */
	@RequestMapping("/firestations")
	public Iterable<Firestation> getFirestations() {
		return firestationService.getFirestations();
	}

	@GetMapping("firestations/{id}")

	public Optional<Firestation> showFirestations(@PathVariable Long id) {

		return firestationService.getFirestations(id);
	}
	
	@GetMapping("firestation/{stationNumber}")

	public Iterable<Person> showFirestation(@PathVariable(name = "stationNumber") Long id) {

		Optional<Firestation> firestation = firestationService.getFirestations(id);
		//PersonUtil.ageFromBirthdate(birthdate);
		return personService.getPersonsByAdress(firestation.get().getAddress());
		
	}

	@PostMapping("/firestations")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		Firestation firestationAdded = firestationService.save(firestation);

		if (firestationAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(firestationAdded.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("firestations/{id}")
	public void deleteFirestation(@PathVariable Long id) {

		firestationService.deleteFirestation(id);
	}

	@PutMapping("firestations/{id}")
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
