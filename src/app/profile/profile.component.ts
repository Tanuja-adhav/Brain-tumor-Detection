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
    const storedUser = localStorage.getItem('user'); // ✅ Get user object
    if (storedUser) {
      const user = JSON.parse(storedUser);
      const userId = user.userId; // ✅ Extract userId
      console.log("Logged-in userId:", userId);
  
      this.httpClient.get(`http://localhost:8080/${userId}`).subscribe( // ✅ Use correct URL
        (response) => {
          console.log("User Profile Data:", response);
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
    localStorage.removeItem('email'); // Clear user session
    this.router.navigate(['/login']); // Redirect to login page
  }
}

