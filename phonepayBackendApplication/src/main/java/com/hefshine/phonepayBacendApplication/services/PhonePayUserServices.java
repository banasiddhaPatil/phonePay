package com.hefshine.phonepayBacendApplication.services;

import com.hefshine.phonepayBacendApplication.model.PhonePayUser;
import com.hefshine.phonepayBacendApplication.model.Transactions;

import java.util.List;

public interface PhonePayUserServices {
    boolean userRegister(String mobileNumber);

    boolean verifyOTP( String mobNo,int otp);

    int setPassword(String[] str);

    int signIn(String mobileNo, String password);

    PhonePayUser getPhonePayUser(int id);

    List<Transactions> getAllTransactions(int id);

    List<String> getInfo(int id);
}
