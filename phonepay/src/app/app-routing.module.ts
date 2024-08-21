import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './dashboard/profile/profile.component';

const routes: Routes = [
  {
    path:"",component:HomeComponent
  },
  {
    path:"login",component:LoginComponent
  },
  {
    path:"regester",component:RegisterComponent
  },
  {
    path:"dashboard",component:DashboardComponent
  },
  {
    path:"profile",component:ProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
