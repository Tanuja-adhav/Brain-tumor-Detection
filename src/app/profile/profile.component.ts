import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: any = {};

  constructor(private userService: UserService, private router: Router,private httpClient:HttpClient) {}
  ngOnInit() {
    const email = localStorage.getItem('email'); // ✅ Retrieve stored userId
    console.log(localStorage.getItem('email'));
    if (!email) {
      alert("You are not logged in!");
      this.router.navigate(['/login']);
      return;
    }
  
    // Fetch user profile based on userId
    this.httpClient.get(`http://localhost:8080/${email}`).subscribe(
      (response) => {
        console.log("User Profile Data:", response);
        this.user = response;
      },
      (error) => {
        console.error('Error fetching user profile:', error);
        alert("Error fetching profile. Please try again.");
      }
    );
  }
  
  
  logout() {
    localStorage.removeItem('email'); // Clear user session
    this.router.navigate(['/login']); // Redirect to login page
  }
}

