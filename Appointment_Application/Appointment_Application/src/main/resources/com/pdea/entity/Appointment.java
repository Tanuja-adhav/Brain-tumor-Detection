package com.pdea.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

package com.example.appointment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
