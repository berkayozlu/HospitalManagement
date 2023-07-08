package com.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="appointment",schema = "hospital")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="appointment_id")
	private Integer appointment_id;
	
	@Column(name="patientName")
	private String patientName;
	
	@Column(name="doctor_name")
	private String doctorName;
	
	@Column(name="appointment_date")
	private String date;

	@Column(name="prescription")
	private String prescription;
	
	@Column(name="confirmed")
	private String confirmed;
	


	
}
