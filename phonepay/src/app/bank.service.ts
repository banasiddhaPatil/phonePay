import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private httpServices:HttpClient) { }

  baseUrl:string="http://localhost:8080"

  public getAllBaknDetauls(mobNo:string):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/getBankDetails/${mobNo}`)
  }

  public setId(num:any):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/setUser/${num}`)
  }

  public linkBankAccount(obj:any):Observable<any>{

    return this.httpServices.post<any>(`${this.baseUrl}/linkBankAccount`,obj);
  }
  public linkedList(obj:any):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/getLinkedList/${obj}`);
  }

  public verifyupiId(obj:any):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/verifyUpiId/${obj}`);
  }

  public sendMoney(obj:any):Observable<any>{
    return this.httpServices.post<any>(`${this.baseUrl}/sendMoney`,obj);
  }

  public getAllTransations(obj:any):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/get/all/transactions/${obj}`);
  }
  public getAllTransationsuser(obj:any):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/get/all/transactions/${obj}`);
  }
  public userInfo(obj:any):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/get/user/${obj}`)
  }

  public checkBankBalance(obj:any):Observable<any>{
    return this.httpServices.get<any>(`${this.baseUrl}/get/bank/balance/${obj}`);
  }
}
