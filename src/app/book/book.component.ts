import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';
@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent {
  public book = new FormGroup({
    name: new FormControl(''),
    date : new FormControl(''),
    option: new FormControl('')
  });
  public handleSubmit() {
    
  }
}
