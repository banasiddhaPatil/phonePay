package com.hefshine.phonepayBacendApplication.controller;

import com.hefshine.phonepayBacendApplication.model.dto.ResponseVerifyUserDTO;
import com.hefshine.phonepayBacendApplication.model.dto.SendMoneyDTO;
import com.hefshine.phonepayBacendApplication.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    PaymentServices paymentServices;

    @GetMapping("verifyUpiId/{upiId}")
    public ResponseVerifyUserDTO verifyUPI(@PathVariable String upiId ){
        return paymentServices.verifyUPI(upiId);
    }

    @PostMapping("sendMoney")
    public int sendMoney(@RequestBody SendMoneyDTO sendMoneyDTO){
       // System.out.println(sendMoneyDTO.toString());
        return paymentServices.sendMoneyDTO(sendMoneyDTO);
    }


}
