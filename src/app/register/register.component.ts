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
      name : new FormControl(''),
      password: new FormControl('')
    });
  
    constructor(private httpClient: HttpClient) {}
  
    public handleSubmit() {
      console.log(this.register.value);
      
      this.httpClient.post<boolean>('http://localhost:8080/addUser', this.register.value).subscribe(
        (response) => { 
          // console.log('Response:', response);
          alert("Registration Successfully!!");
        },
        (error) => {
          // console.error('Error:', error);
          alert("Something went wrong. Please try again.");
        }
      );
    }
    
}
