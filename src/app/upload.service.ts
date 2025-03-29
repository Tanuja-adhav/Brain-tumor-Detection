import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private springBootUrl = 'http://localhost:8080/api/upload';  
  private flaskUrl = 'http://127.0.0.1:5000/predict';        

  constructor(private http: HttpClient) {}

  uploadFile(file: File): Observable<{ tumor_type: string }> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post<{ path: string }>(this.springBootUrl, formData).pipe(
      tap((response) => console.log("✅ Spring Boot Response:", response)),  // ✅ Debugging log
      switchMap((response) => {
        if (!response || !response.path) {
          throw new Error("Image upload failed, no path returned from backend.");
        }

        const imagePath = response.path;
        console.log("📤 Sending to Flask:", JSON.stringify({ path: imagePath })); 

        return this.http.post<{ tumor_type: string }>(
          this.flaskUrl,
          { path: imagePath },  
          { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }
        );
        
      }),
      tap((prediction) => console.log("✅ Flask Response:", prediction)),  
      catchError((error) => {
        console.error("❌ Error uploading file or getting prediction:", error);
        return throwError(() => new Error("Error processing image."));
      })
    );
  }
}
