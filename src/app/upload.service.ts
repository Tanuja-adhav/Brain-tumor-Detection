// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable, map } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class UploadService {
//   private apiUrl = 'http://localhost:8080/api/upload';

//   constructor(private http: HttpClient) {}

//   uploadFile(file: File): Observable<string> {
//     const formData = new FormData();
//     formData.append('file', file);

//     return this.http.post<any>(this.apiUrl, formData).pipe(
//       map(response => response.prediction)
//     );
//   }
// }
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private apiUrl = 'http://localhost:8080/api/upload'; // Spring Boot endpoint

  constructor(private http: HttpClient) {}

  uploadFile(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<any>(this.apiUrl, formData); // Full JSON response expected
  }
}
