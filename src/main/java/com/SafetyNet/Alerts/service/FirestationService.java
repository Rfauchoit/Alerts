package com.SafetyNet.Alerts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SafetyNet.Alerts.model.Firestation;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.repository.FirestationRepository;

import lombok.Data;

@Data
@Service
public class FirestationService {

	@Autowired
	private FirestationRepository firestationRepository;

	public Optional<Firestation> getFirestations(final Long id) {
		return firestationRepository.findById(id);
	}

	public Iterable<Firestation> getFirestations() {
		return firestationRepository.findAll();
	}

	public void deleteFirestation(final Long id) {
		firestationRepository.deleteById(id);
	}

	public Iterable<Firestation> save(List<Firestation> firestations) {
		return firestationRepository.saveAll(firestations);
	}

	public Firestation save(Firestation firestation) {
		return firestationRepository.save(firestation);
	}

	public Firestation getFirestationByAddress(String address) {
		return firestationRepository.findFirestationByAddress(address);
	}

}