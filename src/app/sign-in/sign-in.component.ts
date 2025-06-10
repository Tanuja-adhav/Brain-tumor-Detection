import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {
  public data = new FormGroup({
    userId: new FormControl(''), // Using userId for login
    password: new FormControl('')
  });

  constructor(private httpClient: HttpClient, private router: Router) {}

  public handleSubmit() {
    console.log(this.data.value);  // Logging form data

    // Send the login request to the backend
    this.httpClient.post<boolean>('http://localhost:8080/api/loginUser', this.data.value).subscribe(
      (response) => {
        console.log('Response:', response);  // Log the response

        if (response === true) {
          alert("Login successful");

          // Store the user credentials (userId and password) in localStorage
          localStorage.setItem('user', JSON.stringify(this.data.value));  // Store userId and password

          // Redirect to the profile page
          this.router.navigate(['/profile']);
        } else {
          alert("Wrong credentials! Please try again.");
        }
      },
      (error) => {
        console.error('Error:', error);
        alert("Something went wrong. Please try again.");
      }
    );
  }
}
