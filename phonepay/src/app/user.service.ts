import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  getAllTransations(id: number) {
    throw new Error('Method not implemented.');
  }

  constructor(private httpService:HttpClient) { }

  baseURL:String="http://localhost:8080";

  public setUser(number:string):Observable<any>{
    return this.httpService.get<any>(`${this.baseURL}/user/register/${number}`);
  }

  public verifyOTP(mobNo:String,otp:number):Observable<any>{
    return this.httpService.get<any>(`${this.baseURL}/user/verify/${mobNo}/${otp}`)
  }
  public setPassPin(arr:any[]):Observable<any>{
    return this.httpService.post<any>(`${this.baseURL}/setPass/setPin`,arr)
  }
  public signIn(mobNo:string,pass:string):Observable<any>{
    return this.httpService.get<any>(`${this.baseURL}/login/${mobNo}/${pass}`);
  }

  public getPhonePayUser(id:number):Observable<any>{
    return this.httpService.get<any>(`${this.baseURL}/getPhonePayUser/${id}`)
  }
  
}
