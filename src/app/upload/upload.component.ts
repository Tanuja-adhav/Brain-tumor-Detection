import { Component } from '@angular/core';
import { UploadService } from '../upload.service';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;
  prediction: any = null;

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
      next: (response: any) => {
        if (response.structured) {
          this.prediction = response.structured;
        } else {
          alert("⚠️ Server did not return prediction data.");
        }
      },
      error: (err) => {
        console.error("❌ Upload error:", err);
        alert("❌ Failed to upload or predict image.");
      }
    });
  }

  downloadReport(): void {
    const element = document.getElementById('report-content');
    if (!element) return;

    html2canvas(element).then(canvas => {
      const imgData = canvas.toDataURL('image/png');
      const pdf = new jsPDF({
        orientation: 'portrait',
        unit: 'mm',
        format: 'a4'
      });

      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;

      pdf.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight);
      pdf.save('Brain_Tumor_Report.pdf');
    });
  }
}


// import { Component } from '@angular/core';
// import { UploadService } from '../upload.service';

// @Component({
//   selector: 'app-upload',
//   templateUrl: './upload.component.html',
//   styleUrls: ['./upload.component.css']
// })
// export class UploadComponent {
//   selectedFile: File | null = null;
//   imagePreview: string | ArrayBuffer | null = null;
//   prediction: string | null = null;

//   constructor(private uploadService: UploadService) {}

//   onFileSelected(event: Event): void {
//     const fileInput = event.target as HTMLInputElement;

//     if (fileInput.files && fileInput.files.length > 0) {
//       this.selectedFile = fileInput.files[0];

//       const reader = new FileReader();
//       reader.onload = () => {
//         this.imagePreview = reader.result;
//       };
//       reader.readAsDataURL(this.selectedFile);
//     }
//   }

//   onUpload(): void {
//     if (!this.selectedFile) {
//       alert("❌ Please select an image first!");
//       return;
//     }

//     this.uploadService.uploadFile(this.selectedFile).subscribe({
//       next: (prediction: string) => {
//        this.prediction = `Tumor Type: ${prediction}`;

//       },
//       error: (err) => {
//         console.error("❌ Error:", err);
//         this.prediction = "Error processing image.";
//       }
//     });
//   }
// }




