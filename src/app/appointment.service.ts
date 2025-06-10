// src/app/appointment/appointment.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Appointment {
  patientName: string;
  email: string;
  phone: string;
  doctorName: string;
  appointmentDate: string;
}

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private baseUrl = 'http://localhost:8080/appointments';  // Adjust your backend URL here

  constructor(private http: HttpClient) {}

  bookAppointment(appointment: Appointment): Observable<Appointment> {
    return this.http.post<Appointment>(`${this.baseUrl}/book`, appointment);

  }

  getAllAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.baseUrl}/all`);
  }
}
