package com.SafetyNet.Alerts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SafetyNet.Alerts.model.Firestation;
import com.SafetyNet.Alerts.model.Person;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Long> {

	Firestation findFirestationByAddress(String address);
}
