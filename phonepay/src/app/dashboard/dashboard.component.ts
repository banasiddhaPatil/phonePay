import { Component } from '@angular/core';
import { BankUser } from './bank-user';
import { UserService } from '../user.service';
import { PhonePayUser } from './phone-pay-user';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  phonePayUser:PhonePayUser={id:0,name:'',mobNumber:''};

  whatToShow:number=0;

  constructor(private http:UserService,private appCom:AppComponent){}
ngOnInit(){
  this.http.getPhonePayUser(this.appCom.phonePayUserId).subscribe((respone)=>{
    this.phonePayUser=respone;
  });
}
onClick(num:number){
  this.whatToShow=num;
}
    
}
