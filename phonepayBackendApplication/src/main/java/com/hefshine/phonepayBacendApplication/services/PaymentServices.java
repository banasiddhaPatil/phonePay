package com.hefshine.phonepayBacendApplication.services;

import com.hefshine.phonepayBacendApplication.model.Transactions;
import com.hefshine.phonepayBacendApplication.model.dto.ResponseVerifyUserDTO;
import com.hefshine.phonepayBacendApplication.model.dto.SendMoneyDTO;

public interface PaymentServices {
    ResponseVerifyUserDTO verifyUPI(String upiId);


    int sendMoneyDTO(SendMoneyDTO sendMoneyDTO);
}
