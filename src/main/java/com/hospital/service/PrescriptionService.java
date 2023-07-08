package com.hospital.service;

import java.util.List;

import com.hospital.entity.Prescription;
import com.hospital.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrescriptionService {
	
	private final PrescriptionRepository repository;

	public PrescriptionService(PrescriptionRepository repository) {
		this.repository = repository;
	}

	public void save(Prescription Prescription) {
		repository.save(Prescription);
	}
	
	public List<Prescription> findByPatientName(String patientName){
		
			return repository.findByPatientName(patientName);
	}
}
