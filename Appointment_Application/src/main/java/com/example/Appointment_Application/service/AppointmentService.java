
package com.example.Appointment_Application.service;

import com.example.Appointment_Application.Repo.AppointRepo;
import com.example.Appointment_Application.entity.Appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointRepo  appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus("Pending"); 
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    public void cancelAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }
}



