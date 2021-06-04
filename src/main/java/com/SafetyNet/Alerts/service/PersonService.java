package com.SafetyNet.Alerts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	public Optional<Person> getPersons(final Long id) {
		return personRepository.findById(id);
	}

	public Iterable<Person> getPersons() {
		return personRepository.findAll();
	}

	public void deletePerson(final Long id) {
		personRepository.deleteById(id);
	}

	public Iterable<Person> save(List<Person> persons) {
		return personRepository.saveAll(persons);
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public void deletePerson(String firstName, String lastName) {
		// TODO Auto-generated method stub

	}

	public Optional<Person> getPersons(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Person> getPersonsByAdress(String address) {
		return personRepository.findPersonByAdress(address);
	}

}