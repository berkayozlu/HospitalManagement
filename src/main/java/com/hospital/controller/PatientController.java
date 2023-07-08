package com.hospital.controller;

import java.util.List;

import com.hospital.entity.Appointment;
import com.hospital.service.AppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientController {

	private final AppointmentService appointmentService;

	public PatientController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}


	@GetMapping("/patientAppointments")
	public String myAppointments(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String patientName = authentication.getName();
		List<Appointment> listAppointments = appointmentService.findByPatientName(patientName);
		model.addAttribute("listAppointments",listAppointments);
		return "patientAppointments";
	}
	
	@GetMapping("/cancelAppointment")
	public String cancelAppointment(Model model) {
		Appointment cancelAppointment = new Appointment();
		model.addAttribute("appointment",cancelAppointment);
		return "cancelAppointment";
	
	}
	
	
	
}
