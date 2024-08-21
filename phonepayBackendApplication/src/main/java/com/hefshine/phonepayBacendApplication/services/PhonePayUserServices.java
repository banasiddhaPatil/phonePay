package com.hefshine.phonepayBacendApplication.services;

import com.hefshine.phonepayBacendApplication.model.PhonePayUser;

public interface PhonePayUserServices {
    boolean userRegister(String mobileNumber);

    boolean verifyOTP( String mobNo,int otp);

    int setPassword(String[] str);

    int signIn(String mobileNo, String password);

    PhonePayUser getPhonePayUser(int id);
}
