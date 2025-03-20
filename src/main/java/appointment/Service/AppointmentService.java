package appointment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appointment.Entity.User;
import appointment.Repo.UserRepository;

@Service
public class AppointmentService {

    @Autowired
    private UserRepository appRepository;

    public User bookAppointment(User appointment) {
        return appRepository.save(appointment);
    }

    public List<User> getAppointments() {
        return appRepository.findAll();
    }
}