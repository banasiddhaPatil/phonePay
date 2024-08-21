package com.hefshine.phonepayBacendApplication.services;

import com.hefshine.phonepayBacendApplication.model.BankUser;

import java.util.List;

public interface BankUserServices {
    List<BankUser> getAllBankDetails(String mobNo);
}
