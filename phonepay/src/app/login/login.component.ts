import { HttpClient, withNoXsrfProtection } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

 
  mobNo:string="9604797731";
  passWord:string="1234";
  constructor(private router:Router,private myServices:UserService,private appCom:AppComponent){}

  onClick(){
   
   
  }
  onSubmit(){
    this.myServices.signIn(this.mobNo,this.passWord).subscribe((response)=>{
      if(response!=-1){
        this.appCom.phonePayUserId=response;
        this.router.navigate(["/dashboard"]);
      }
      else{
        window.alert("wrong credentials!!!");
      }
    });
  }

}
