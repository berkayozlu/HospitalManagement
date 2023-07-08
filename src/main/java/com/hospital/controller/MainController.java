package com.hospital.controller;

import com.hospital.entity.Prescription;
import com.hospital.entity.Appointment;
import com.hospital.service.AppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class MainController {

	private final AppointmentService appointmentService;

	public MainController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@GetMapping({"/", "/main"})
	public String showMain() {
		return "main";
	}
    

	@GetMapping("/patients")
	public String showPatient(Model model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName();
		model.addAttribute("username",username);
		String id =  (String) model.asMap().get("appointmentId");
		model.addAttribute("appointmentId",id);
		return "patients";
	}
	
	@GetMapping("/doctors")
	public String showDoctors(Model model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String doctorName = authentication.getName();
		List<Appointment> doctorAppointments = appointmentService.findByDoctorName(doctorName);
		model.addAttribute("doctorAppointments",doctorAppointments);
		model.addAttribute("username",doctorName);
		return "doctors";
	}
	
	

	
	@GetMapping("/receptionist")
	public String showReceptionist(Model model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

		List<Appointment> listAppointments = appointmentService.listAll();
		model.addAttribute("listAppointments",listAppointments);
		String username= authentication.getName();
		model.addAttribute("username",username);
		String confirmed = "confirmed";
		model.addAttribute("confirmed",confirmed);
		return "receptionist";
	}

	
	

		@GetMapping("/add")
		public String newAppointment(Model model) {
			Appointment appointment = new Appointment();
			appointment.setConfirmed("Confirmation pending approval");
			model.addAttribute("appointment",appointment);
			return "add";
		}
		
		@GetMapping("/save")
		public String saveProduct(@ModelAttribute("appointment") Appointment appointment,
				BindingResult result, ModelMap model,
				RedirectAttributes redirectAttributes
				) {
			appointment.setConfirmed("Pending confirmation");
		   appointmentService.save(appointment);
		   String appointmentId=appointment.getAppointment_id().toString();
		   String message = "Appointment has been successfully scheduled, and your unique ID is: "+appointmentId;
		   redirectAttributes.addFlashAttribute("message", message);
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   redirectAttributes.addFlashAttribute("appointmentId",appointmentId);
		   return "redirect:/patients";
		   
		}
		
		
		@GetMapping("/cancel")
		public String cancel(@ModelAttribute("appointment") Appointment appointment,
				BindingResult result, ModelMap model,
				RedirectAttributes redirectAttributes
				) {
		   Integer id = appointment.getAppointment_id();
		   appointmentService.delete(id);
		  String message = "Appointment has been effectively terminated!";
		  redirectAttributes.addFlashAttribute("message", message);
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		    return "redirect:/patients";
		   
		}
		
		@RequestMapping("/confirm")
		public String confirm(@ModelAttribute("appointment") Appointment appointment, BindingResult result, ModelMap model,
							  RedirectAttributes redirectAttributes
				) {
			System.out.println(appointment);
		 String confirmation = "confirmed";
		 Integer id = appointment.getAppointment_id();
		 appointmentService.setConfirmation(confirmation, id);
		System.out.println(id);
		  String message = "Appointment confirmed!";
		  redirectAttributes.addFlashAttribute("message", message);
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		    return "redirect:/receptionist";
		   
		}
		
		@GetMapping("/confirmit")
		public String showConfirmIt(Model model) {
			Appointment confirmation = new Appointment();
			model.addAttribute("confirmation",confirmation);
			return "confirmed";
		}
		


		
		@GetMapping("/prescribe")
		public String prescribe(Model model) {
			Prescription ps = new Prescription();
			model.addAttribute("prescription",ps);
			return "prescribe";
		}


		

		
}
