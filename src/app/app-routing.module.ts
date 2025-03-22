import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ProfileComponent } from './profile/profile.component';
import { UploadComponent } from './upload/upload.component';

const routes: Routes = [
  {
    path:'',redirectTo:'home',pathMatch:'full'
  },
  {
    path:'home',component:HomeComponent
  },
  {
    path:'home',component:AppComponent
  },
  {
        path:'profile',component:ProfileComponent
  },
  {
    path:'CheckReport',component:UploadComponent
  },
  {
     path:'home',
    children:[
      {
        path:'',component:HomeComponent
      },
      {
        path:'login',component:SignInComponent
      },
      {
        path:'register',component:RegisterComponent
      },{
        path:'CheckReport',component:UploadComponent
      }
    ]
  },
  {
    path:'register',component:RegisterComponent
  },
  {
    path:'login',component:SignInComponent
  },
  {
    path:'mri',component:UploadComponent
  },
  {
    path:'',component:HomeComponent
  },
  {
    path:'**',component:PageNotFoundComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
 }
