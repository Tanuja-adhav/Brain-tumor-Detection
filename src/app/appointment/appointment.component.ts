// src/app/appointment/appointment.component.ts
import { Component } from '@angular/core';
import { Appointment, AppointmentService } from '../appointment.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styles: [`
    .appointment-box {
      max-width: 500px;
      margin: 40px auto;
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 8px 20px rgba(0,0,0,0.1);
      background-color: #ffffff;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .appointment-box h2 {
      text-align: center;
      margin-bottom: 20px;
      color: #2c3e50;
    }

    .appointment-box input {
      width: 100%;
      padding: 10px 12px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 8px;
      font-size: 16px;
    }

    .appointment-box input:focus {
      border-color: #3498db;
      outline: none;
      box-shadow: 0 0 4px rgba(52, 152, 219, 0.5);
    }

    .appointment-box button {
      width: 100%;
      padding: 12px;
      background-color: #3498db;
      color: white;
      font-size: 16px;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .appointment-box button:hover {
      background-color: #2980b9;
    }
  `]
})
export class AppointmentComponent {
  appointment: Appointment = {
    patientName: '',
    email: '',
    phone: '',
    doctorName: '',
    appointmentDate: ''
  };

  constructor(private appointmentService: AppointmentService) {}
bookAppointment() {
  console.log('bookAppointment() triggered');
  this.appointmentService.bookAppointment(this.appointment).subscribe({
    next: (response) => {
      console.log('Backend response:', response);
      alert('Appointment booked successfully!');
      this.resetForm();
    },
    error: (err) => {
      alert('Failed to book appointment. Please try again.');
      console.error(err);
    }
  });
}



  resetForm() {
    this.appointment = {
      patientName: '',
      email: '',
      phone: '',
      doctorName: '',
      appointmentDate: ''
    };
  }
  ngOnInit(): void {
  console.log('AppointmentComponent loaded');
}
}

