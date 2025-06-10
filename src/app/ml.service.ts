// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root',
// })
// export class MlService {
//   private apiUrl = 'http://127.0.0.1:5000/predict';  // ✅ Ensure URL is correct

//   constructor(private http: HttpClient) {}

//   uploadImage(file: File): Observable<any> {
//     const formData = new FormData();
//     formData.append('file', file);

//     const headers = new HttpHeaders({
//       'Accept': 'application/json'
//     });

//     return this.http.post<any>(this.apiUrl, formData, { headers });  // ✅ Ensure this is POST
//   }


// }
