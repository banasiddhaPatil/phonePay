package com.hefshine.phonepayBacendApplication.services;

import com.hefshine.phonepayBacendApplication.model.BankUser;
import com.hefshine.phonepayBacendApplication.model.Transactions;
import com.hefshine.phonepayBacendApplication.model.dto.BalanceCheckDTO;

import java.util.List;

public interface BankUserServices {
    List<BankUser> getAllBankDetails(String mobNo);

    List<BalanceCheckDTO> checkBalace(int id);
}
