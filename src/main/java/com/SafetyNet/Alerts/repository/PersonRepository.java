package com.SafetyNet.Alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SafetyNet.Alerts.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {


}
