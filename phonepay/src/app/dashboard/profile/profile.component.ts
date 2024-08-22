import { Component,Input } from '@angular/core';
import { Router } from '@angular/router';
import { BankService } from 'src/app/bank.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  @Input() phonePayUserProp={id:0,name:'',mobNumber:''};

constructor(private myServices:BankService,private route:Router){
 
}

bankList:any[]|undefined;
linkdbankList!:any[];
whatToShow=0;


ngOnInit(){
  this.myServices.getAllBaknDetauls(this.phonePayUserProp.mobNumber).subscribe({
    next: (response) => {
      this.bankList = response;
    },
    error: (err) => {
      this.route.navigate([""]);
      // You can also display an error message to the user
    },
  });
}

onClick(){
this.whatToShow=1;
}
}
