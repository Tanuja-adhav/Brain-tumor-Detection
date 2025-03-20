package appointment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appointment.Entity.User;
import appointment.Service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public User bookAppointment(@RequestBody User appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @GetMapping
    public List<User> getAppointments() {
        return appointmentService.getAppointments();
    }
}
