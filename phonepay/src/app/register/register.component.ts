import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AppModule } from '../app.module';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private myServices:UserService,private router:Router,private appModule:AppComponent){}

  mobNumber:string="";
  otp:number=0;
  password:string="";
  conformPassword="";
  upiPin:number=0;

  whatToDo:number=0;

  onClick(){
    this.myServices.setUser(this.mobNumber).subscribe((response)=>{
      if(response==true){
      this.whatToDo=1;
      }
      else{
        window.alert("Your mobile number is already registered.");
      }
    });
  }
  onSumbit(){
    this.myServices.verifyOTP(this.mobNumber,this.otp).subscribe((response)=>{
      if(response==true){
      this.whatToDo=2;
      }
      if(response==false){
      window.alert("Enter Wrong OTP");
      }
    });
  }
  onSumbitPassword(){
    if(this.password==this.conformPassword){
        this.whatToDo=3;
    }
    else {
      window.alert("Password not Match!!!");
    }
  }
  onSumbitPin(){
    let arr:[string,string,number];
    arr=[this.mobNumber,this.password,this.upiPin];
   this.myServices.setPassPin(arr).subscribe((response:any)=>{
    if(response!=-1){
      this.appModule.phonePayUserId=response;
      this.router.navigate((["/dashboard"]))
    }
    else{
      window.alert("Somthing Wrong");
    }
   });
  }
}
