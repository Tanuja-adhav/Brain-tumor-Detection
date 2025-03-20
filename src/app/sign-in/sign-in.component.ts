/*import { Component } from '@angular/core';
// import { FormControl, FormGroup } from '@angular/forms';
// import { HttpClient } from '@angular/common/http';

// @Component({
//   selector: 'app-sign-in',
//   templateUrl: './sign-in.component.html',
//   styleUrls: ['./sign-in.component.css']
// })
// export class SignInComponent {
//   public data = new FormGroup({
//     userId: new FormControl(''),
//     password: new FormControl('')
//   });

//   constructor(private httpClient: HttpClient) {}

//   public handleSubmit() {
//     console.log(this.data.value);
    
//     this.httpClient.post<boolean>('http://localhost:8080/loginUser', this.data.value).subscribe(
//       (response) => {
//         console.log('Response:', response);
        
//         if (response === true) {
//           alert("Login successfully");
//         } else {
//           alert("Wrong credentials!!!Please try again.");
//         }
//       },
//       (error) => {
//         console.error('Error:', error);
//         alert("Something went wrong. Please try again.");
//       }
//     );
//   }
}*/
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
    userId: new FormControl(''), // Keeping userId instead of email
    password: new FormControl('')
  });

  constructor(private httpClient: HttpClient, private router: Router) {}

  public handleSubmit() {
    console.log(this.data.value);

    this.httpClient.post<boolean>('http://localhost:8080/loginUser', this.data.value).subscribe(
      (response) => {
        console.log('Response:', response);

        if (response === true) {
          alert("Login successful");

          // Store user info in localStorage
          console.log(localStorage.getItem('email'));
          localStorage.setItem('user', JSON.stringify(this.data.value));

          // Redirect to Profile Page
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

