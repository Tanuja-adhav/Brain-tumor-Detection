import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { BookComponent } from './book/book.component';
import { ProfileComponent } from './profile/profile.component';
import { UploadComponent } from './upload/upload.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppointmentComponent } from './appointment/appointment.component';


@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    UploadComponent,
    RegisterComponent,
    HomeComponent,
    BookComponent,
    ProfileComponent,
    AppointmentComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}
