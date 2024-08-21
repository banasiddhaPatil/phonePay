import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  
  constructor(private router:Router,private appCom:AppComponent){}



  onClick(){
    this.router.navigate(["/login"]);
  }
  onLogin(){
    window.alert("Login First !!!");
  }

}
