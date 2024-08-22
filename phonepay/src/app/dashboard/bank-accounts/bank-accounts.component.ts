import { Component ,Input} from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { BankService } from 'src/app/bank.service';

@Component({
  selector: 'app-bank-accounts',
  templateUrl: './bank-accounts.component.html',
  styleUrls: ['./bank-accounts.component.css']
})
export class BankAccountsComponent {

  @Input() phonePayUserProp={id:0,name:'',mobNumber:''};


  constructor(private myServices:BankService,private appCom:AppComponent){
  }
  ngOnInit(){
    //this.onCall1();
   this.onCall();
  }

  onCall(){
    this.myServices.getAllBaknDetauls(this.phonePayUserProp.mobNumber).subscribe((resonse)=>{
      this.bankList=resonse;
  });
  
  this.myServices.linkedList(this.phonePayUserProp.mobNumber).subscribe((res:any)=>{
this.linkdbankList=res;
});
  }
   
  whatToShow=0;
  linkdbankList!:any[];
  bankList!:any[];
  babkId!:number;
  otp:number=0;
  upiPin:number=0;
 ;

  onClick(id:number){
    this.babkId=id;
    this.myServices.setId(id).subscribe((response)=>{
      if(response==1){
        this.whatToShow=1;
      }
      else{
        window.alert("Somthing is wrong");
      }
    });
  }
  linkAccount(){
   
     let obj={
      "phonePayUserId":this.appCom.phonePayUserId,
      "bankUserId":this.babkId,
      "upiPin":this.upiPin,
      "otp":this.otp
     }
     this.myServices.linkBankAccount(obj).subscribe((response)=>{
      console.log(response);
      this.onCall();
      this.whatToShow=0;
     });
  }
}
