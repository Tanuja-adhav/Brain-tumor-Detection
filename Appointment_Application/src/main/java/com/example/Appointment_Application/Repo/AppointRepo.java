package com.example.Appointment_Application.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Appointment_Application.entity.Appointment;

public interface AppointRepo extends JpaRepository<Appointment, Integer> {

}
