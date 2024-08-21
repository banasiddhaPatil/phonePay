import { Component,Input } from '@angular/core';
import { BankService } from 'src/app/bank.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  @Input() phonePayUserProp={id:0,name:'',mobNumber:''};

constructor(private myServices:BankService){
 
}

bankList:any[]|undefined;
linkdbankList!:any[];
whatToShow=0;


ngOnInit(){
  this.myServices.getAllBaknDetauls(this.phonePayUserProp.mobNumber).subscribe((resonse)=>{
    this.bankList=resonse;
    console.log(resonse);
});
}

onClick(){
this.whatToShow=1;
}
}
