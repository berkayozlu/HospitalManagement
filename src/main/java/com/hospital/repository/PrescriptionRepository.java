package com.hospital.repository;

import java.util.List;

import com.hospital.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,String> {

	List<Prescription> findByPatientName(String patientName);
}
