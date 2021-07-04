package com.SafetyNet.Alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.PersonService;
import java.net.URI;
import java.util.Optional;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	/**
	 * Read - Get all persons
	 * 
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/person")
	public Iterable<Person> getPersons() {
		return personService.getPersons();
	}

	@GetMapping("person/{id}")

	public Optional<Person> showPerson(@PathVariable Long id) {

		return personService.getPersons(id);
	}

	@PostMapping("/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		Person personAdded = personService.save(person);

		if (personAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(personAdded.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("person")
	public void deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

		personService.deletePerson(firstName, lastName);
	}

	@PutMapping("person")
	// public ResponseEntity<Person>
	Person updatePerson(@RequestBody Person person) {
		return personService.getPersons(person.getFirstName(), person.getLastName()).map(persons -> {
			person.setAddress(person.getAddress());
			person.setCity(person.getCity());
			person.setEmail(person.getEmail());
			person.setPhone(person.getPhone());
			person.setZip(person.getZip());
			return personService.save(person);
		}).orElseGet(() -> {
			return personService.save(person);
		});
	}
}