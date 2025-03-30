package com.pdea.controller;
package com.example.appointment.controller;

import com.example.appointment.model.Appointment;
import com.example.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ✅ POST: Book an appointment
    @PostMapping("/book")
    public Map<String, String> bookAppointment(@RequestBody Appointment appointment) {
        appointmentService.bookAppointment(appointment);
        return Map.of("message", "Your appointment has been successfully booked!");
    }

    // ✅ GET: Fetch all appointments
    @GetMapping("/history")
    public List<Appointment> getAppointments() {
        return appointmentService.getAllAppointments();
    }

    // ✅ GET: Fetch appointment by ID
    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    // ✅ DELETE: Cancel an appointment
    @DeleteMapping("/cancel/{id}")
    public Map<String, String> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return Map.of("message", "Your appointment has been successfully cancelled!");
    }
}
