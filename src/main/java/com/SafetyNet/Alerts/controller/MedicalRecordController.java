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

import com.SafetyNet.Alerts.model.MedicalRecord;
import com.SafetyNet.Alerts.model.Person;
import com.SafetyNet.Alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
	@Autowired
	private MedicalRecordService medicalRecordService;

	/**
	 * Read - Get all medical records
	 * 
	 * @return - An Iterable object of Employee full filled
	 */
	@RequestMapping("/medicalRecords")
	public Iterable<MedicalRecord> getMedicalRecords() {
		return medicalRecordService.getMedicalRecords();
	}

	@GetMapping("medicalRecords/{id}")

	public Optional<MedicalRecord> showMedicalRecord(@PathVariable Long id) {

		return medicalRecordService.getMedicalRecords(id);
	}

	@PostMapping("/medicalRecords")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordAdded = medicalRecordService.save(medicalRecord);

		if (medicalRecordAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(medicalRecordAdded.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/medicalRecords")
	public void deleteMedicalRecord(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {

		medicalRecordService.deleteMedicalRecord(firstName, lastName);

	}

	@PutMapping("medicalRecords/{id}")
	MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, @PathVariable Long id) {
		return medicalRecordService.getMedicalRecords(id).map(medicalRecords -> {
			medicalRecord.setBirthdate(medicalRecord.getBirthdate());
			medicalRecord.setAllergies(medicalRecord.getAllergies());
			medicalRecord.setMedications(medicalRecord.getMedications());

			return medicalRecordService.save(medicalRecord);
		}).orElseGet(() -> {
			medicalRecord.setId(id);
			return medicalRecordService.save(medicalRecord);
		});
	}
}
