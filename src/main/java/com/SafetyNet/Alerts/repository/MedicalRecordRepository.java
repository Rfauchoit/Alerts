package com.SafetyNet.Alerts.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SafetyNet.Alerts.model.MedicalRecord;
import com.SafetyNet.Alerts.model.Person;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

	@Modifying
	@Query("delete from MedicalRecord m where m.firstName=?1 and m.lastName=?2")
	void delete(String firstName, String lastName);

	Iterable<MedicalRecord> findMedicalRecordsByFirstNameAndLastName(final String firstName, final String lastName);
}
