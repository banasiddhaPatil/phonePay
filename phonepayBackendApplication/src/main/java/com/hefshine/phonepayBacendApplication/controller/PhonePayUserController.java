package com.hefshine.phonepayBacendApplication.controller;

import com.hefshine.phonepayBacendApplication.model.PhonePayUser;
import com.hefshine.phonepayBacendApplication.services.BankUserServices;
import com.hefshine.phonepayBacendApplication.services.PhonePayUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin
public class PhonePayUserController {
    @Autowired
    PhonePayUserServices phonePayUserServices;


    @GetMapping("user/register/{mobNumber}")
    public boolean userRegister(@PathVariable("mobNumber") String mobileNumber){
        return phonePayUserServices.userRegister(mobileNumber);
    }

    @GetMapping("user/verify/{mobNo}/{otp}")
    @CrossOrigin
    public boolean verifyOTP(@PathVariable("mobNo") String mobNo,@PathVariable("otp") int otp){
        return phonePayUserServices.verifyOTP(mobNo,otp);
    }

    @PostMapping("/setPass/setPin")
    @CrossOrigin
    public int setPassword(@RequestBody String[] str){
        return phonePayUserServices.setPassword(str);
    }

    @GetMapping("/login/{mobNo}/{pass}")
    @CrossOrigin
    public int signIn(@PathVariable String mobNo,@PathVariable String pass){
        return phonePayUserServices.signIn(mobNo,pass);
    }

    @GetMapping("/getPhonePayUser/{id}")
    @CrossOrigin
    public PhonePayUser getPhonePayUser(@PathVariable int id){
        return phonePayUserServices.getPhonePayUser(id);
    }


}
