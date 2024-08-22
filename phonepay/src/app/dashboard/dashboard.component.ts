import { Component } from '@angular/core';
import { BankUser } from './bank-user';
import { UserService } from '../user.service';
import { PhonePayUser } from './phone-pay-user';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  phonePayUser:PhonePayUser={id:0,name:'',mobNumber:''};

  whatToShow:number=0;

  constructor(private http:UserService,private appCom:AppComponent,private route:Router){}
ngOnInit(){
  this.http.getPhonePayUser(this.appCom.phonePayUserId).subscribe({
    next: (response) => {
      this.phonePayUser = response;
    },
    error: (err) => {
      this.route.navigate([""]);
      // You can also display an error message to the user
    },
  });
  
}
onClick(num:number){
  this.whatToShow=num;
}
    
}
