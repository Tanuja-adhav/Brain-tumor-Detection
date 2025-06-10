import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  public register = new FormGroup({
    email: new FormControl(''),
    name: new FormControl(''),
    password: new FormControl('')
  });
  
  constructor(private httpClient: HttpClient) {}

  public handleSubmit() {
    this.httpClient.post<any>('http://localhost:8080/api/addUser', this.register.value).subscribe(
      (response) => {
        alert("Registration Successfully!!");
      },
      (error) => {
        if (error.status === 400 && error.error?.message) {
          alert(error.error.message); // "User already exists" handled here
        } else {
          alert("Something went wrong. Please try again.");
        }
      }
    );
  }
}
