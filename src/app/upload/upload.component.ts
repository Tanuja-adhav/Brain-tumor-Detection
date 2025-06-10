import { Component } from '@angular/core';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;
  prediction: string | null = null;

  constructor(private uploadService: UploadService) {}

  onFileSelected(event: Event): void {
    const fileInput = event.target as HTMLInputElement;

    if (fileInput.files && fileInput.files.length > 0) {
      this.selectedFile = fileInput.files[0];

      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }

  onUpload(): void {
    if (!this.selectedFile) {
      alert("❌ Please select an image first!");
      return;
    }

    this.uploadService.uploadFile(this.selectedFile).subscribe({
      next: (prediction: string) => {
       this.prediction = `Tumor Type: ${prediction}`;

      },
      error: (err) => {
        console.error("❌ Error:", err);
        this.prediction = "Error processing image.";
      }
    });
  }
}



