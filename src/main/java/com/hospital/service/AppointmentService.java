package com.hospital.service;

import java.util.List;

import com.hospital.entity.Appointment;
import com.hospital.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentService {
		
		private final AppointmentRepository bookAppointment;

	public AppointmentService(AppointmentRepository bookAppointment) {
		this.bookAppointment = bookAppointment;
	}


	public List<Appointment> listAll(){
			return bookAppointment.findAll();
		}
		
		public void save(Appointment appointment) {
			bookAppointment.save(appointment);
		}
		
		
		public void delete(Integer id) {
			bookAppointment.deleteById(id);
		}
		
		public int setConfirmation(String confirmation, Integer id) {
			 return bookAppointment.setConfirmation(confirmation, id);
		}
		
		
		public int setPrescription(String confirmation, Integer id) {
			 return bookAppointment.setPrescription(confirmation, id);
		}

	public List<Appointment> findByPatientName(String patientName)
		{
			return bookAppointment.findByPatientName(patientName);
		}
		
		public List<Appointment> findByDoctorName(String doctorName)
		{
			return bookAppointment.findByDoctorName(doctorName);
		}

}

