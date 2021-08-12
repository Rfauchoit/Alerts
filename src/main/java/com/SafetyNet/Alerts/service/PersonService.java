package com.SafetyNet.Alerts.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.Alerts.model.Firestation;
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
	
	public List<Person>getPersonsByAddress(String address) {
		return personRepository.findPersonByAddress(address);
		
	}
	
	public List<Person>getPersonByFirstNameAndLastName(String firstName, String lastName){
		return personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<Person> getPersonByCity(String city) {
		return personRepository.findPersonByCity(city);
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

	public List<Person> getPersonsByAddresses(List<Firestation> addresses) {
		// Création de la liste de personnes avec un New ArrayList
		//Pour chaque adresse faire 
			//Appeler la méthode getPersonByAdress
			//Ajouter cette liste à l'ArrayList qui a été créé avec addAll
		// Return de la liste des personnes 
		return null;
	}
	
}


