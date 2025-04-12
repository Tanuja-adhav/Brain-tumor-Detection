
package com.example.Appointment_Application.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")

public class Appointment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String patientName;
    private String email;
    private String phone;
    private String doctorName;
    private LocalDateTime appointmentDate;
    private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Appointment(Long id, String patientName, String email, String phone, String doctorName,
			LocalDateTime appointmentDate, String status) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.email = email;
		this.phone = phone;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.status = status;
	} 


}
