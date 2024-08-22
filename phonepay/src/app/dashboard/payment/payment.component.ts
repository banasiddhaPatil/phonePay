import { Component,Input } from '@angular/core';
import { Router } from '@angular/router';
import { BankService } from 'src/app/bank.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {
  linkedBankList!:any[];
  selectedBank!:string;
  constructor(private myservices:BankService,private route:Router){  }
  ngOnInit(){
    this.myservices.linkedList(this.phonePayUserProp.mobNumber).subscribe((res:any)=>{
      this.linkedBankList=res;
      });
  }
 
  @Input() phonePayUserProp={id:0,name:'',mobNumber:''};
  whatToShow:number=0;
  upiId:string="";
  ammount!:number;
  remarks!:string;
  UPIPin!:number; 
  reciverName!:string;
  reciverbankName!:string


  onClick(){
    this.myservices.verifyupiId(this.upiId).subscribe((response:any)=>{
      if(response!=null){
         this.reciverName=response.userName;
         this.reciverbankName=response.userBank;
         this.whatToShow=1;
      }
      else{
         window.alert("UPI ID NOT FOUND!!!");
      }
    });


   
  }
  onPayment() {
    let obj = {
        "senderId": Number(this.phonePayUserProp.id),
        "sendAmount": (this.ammount),
        "upiPin": Number(this.UPIPin),
        "receiverUpiId": this.upiId
    };

    console.log('Payment Object:', obj); // Log the object to ensure all fields are populated

    this.myservices.sendMoney(obj).subscribe((res: any) => {
        if(res==1){
          this.whatToShow=3;
        }
        else if(res==-1){
          window.alert("Upi Pin Wrong!!!")
        }
        else if(res==-2){
          window.alert("Insufficeat Balance!!!");
        }
        else{
          window.alert("Interanl Server error!!!");
        }
    });
}
onHome() {
  this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    this.route.navigate(['/dashboard']);
  });
}


}
