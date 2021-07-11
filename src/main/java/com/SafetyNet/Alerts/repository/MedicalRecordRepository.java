package com.SafetyNet.Alerts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SafetyNet.Alerts.model.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

	@Query("delete from MedicalRecord m where m.firstName=?1 and m.lastName=?2")
	void delete(String firstName, String lastName);
	
	Optional<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName);

}
