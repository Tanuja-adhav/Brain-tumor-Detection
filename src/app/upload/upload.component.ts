import { Component } from '@angular/core';
import { UploadService } from '../upload.service';
import { MlService } from '../ml.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  prediction: string | null = null;

  constructor(private mlService: MlService) {}

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.mlService.uploadImage(file).subscribe(
        (response) => {
          this.prediction = response.prediction;
        },
        (error) => {
          console.error('Error uploading file:', error);
          this.prediction = 'Error processing image';
        }
      );
    }
  }
}
