import { Component } from '@angular/core';
import { BankUser } from './bank-user';
import { UserService } from '../user.service';
import { PhonePayUser } from './phone-pay-user';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';
import { BankService } from '../bank.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  phonePayUser:PhonePayUser={id:0,name:'',mobNumber:''};
  transacations!:any[];
 checkBalance!:any[];
  whatToShow:number=0;

  constructor(private httpp:BankService,private http:UserService ,private appCom:AppComponent,private route:Router){}
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
  this.httpp.getAllTransationsuser(this.appCom.phonePayUserId).subscribe((res:any)=>{
    this.transacations=res;
  });

  this.httpp.checkBankBalance(this.appCom.phonePayUserId).subscribe((data:any)=>{
    this.checkBalance=data;
    console.log(data);
  });
}
onClick(num:number){
  console.log(num);
  this.whatToShow=num;
  this.ngOnInit();
}   
}
