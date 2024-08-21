package com.hefshine.phonepayBacendApplication.services;

import com.hefshine.phonepayBacendApplication.model.BankUser;
import com.hefshine.phonepayBacendApplication.model.LinkedBankAccount;
import com.hefshine.phonepayBacendApplication.model.dto.linkedAccountDTO;

import java.util.List;

public interface LinkedBankAccountServices {
    int setUser(int id);

   int linkAccount(LinkedBankAccount linkedBankAccount);

    List  <linkedAccountDTO> getLinkedListUser(String mobNo);
}
