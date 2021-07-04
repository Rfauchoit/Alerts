package com.SafetyNet.Alerts.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SafetyNet.Alerts.model.MedicalRecord;
import com.SafetyNet.Alerts.model.MedicalRecordId;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.repository.MedicalRecordRepository;

import lombok.Data;
@Data
@Service
public class MedicalRecordService {
	   @Autowired
	    private MedicalRecordRepository medicalRecordRepository;

	    public Optional<MedicalRecord> getMedicalRecords(final MedicalRecordId id) {
	        return medicalRecordRepository.findById(id);
	    }

	    public Iterable<MedicalRecord> getMedicalRecords() {
	        return medicalRecordRepository.findAll();
	    }

	    /*@Transactional
	    public void deleteMedicalRecord(final String firstName, final String lastName) {
	    	medicalRecordRepository.delete(firstName, lastName);
	    }*/
	    
	    public Iterable<MedicalRecord> save(List<MedicalRecord> medicalRecords) {
	        return medicalRecordRepository.saveAll(medicalRecords);}
	    
	    public MedicalRecord save(MedicalRecord medicalRecord) {
	        	return medicalRecordRepository.save(medicalRecord);

	 
	    }

	}
