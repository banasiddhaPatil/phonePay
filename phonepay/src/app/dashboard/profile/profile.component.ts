import { Component,Input } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { BankService } from 'src/app/bank.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  @Input() phonePayUserProp={id:0,name:'',mobNumber:''};

constructor(private myServices:BankService,private route:Router,private appcom:AppComponent){
 
}

bankList:any[]|undefined;
linkdbankList!:any[];
userInfo!:any[];

whatToShow=0;


ngOnInit(){
  this.myServices.getAllBaknDetauls(this.phonePayUserProp.mobNumber).subscribe({
    next: (response) => {
      //console.log(response)
      this.bankList = response;
    },
    error: (err) => {
      this.route.navigate([""]);
      // You can also display an error message to the user
    },
  });

  this.myServices.userInfo(this.appcom.phonePayUserId).subscribe((res:any)=>{
    //console.log(res);
this.userInfo=res;
  });


}

onClick(){
this.whatToShow=1;
}
}
