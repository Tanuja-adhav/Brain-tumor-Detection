import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: any = {};

  constructor(private httpClient: HttpClient, private router: Router) {}

  ngOnInit() {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const user = JSON.parse(storedUser);
      const userId = user.userId;
      this.httpClient.get(`http://localhost:8080/api/${userId}`).subscribe(
        (response) => {
          this.user = response;
        },
        (error) => {
          console.error('Error fetching user profile:', error);
          alert("Error fetching profile. Please try again.");
        }
      );
    } else {
      alert("You are not logged in!");
      this.router.navigate(['/login']);
    }
  }
  

  logout() {
    localStorage.removeItem('user');  // Clear user session
    this.router.navigate(['/login']);  // Redirect to login page
  }
}
