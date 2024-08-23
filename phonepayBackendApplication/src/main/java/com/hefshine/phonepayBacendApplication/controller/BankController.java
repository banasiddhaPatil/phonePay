package com.hefshine.phonepayBacendApplication.controller;

import com.hefshine.phonepayBacendApplication.model.BankUser;
import com.hefshine.phonepayBacendApplication.model.LinkedBankAccount;
import com.hefshine.phonepayBacendApplication.model.Transactions;
import com.hefshine.phonepayBacendApplication.model.dto.BalanceCheckDTO;
import com.hefshine.phonepayBacendApplication.model.dto.linkedAccountDTO;
import com.hefshine.phonepayBacendApplication.services.BankUserServices;
import com.hefshine.phonepayBacendApplication.services.LinkedBankAccountServices;
import com.hefshine.phonepayBacendApplication.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BankController {


    @Autowired
    BankUserServices bankUserServices;

    @Autowired
    LinkedBankAccountServices linkedBankAccountServices;
    @GetMapping("getBankDetails/{mobNo}")
    public List<BankUser> getAllBankDetails(@PathVariable String mobNo){
        return bankUserServices.getAllBankDetails(mobNo);
    }

    @GetMapping("setUser/{id}")
    public int setUser(@PathVariable int id){
        return linkedBankAccountServices.setUser(id);
    }

    @PostMapping("linkBankAccount")
    public int linkBankAccount(@RequestBody LinkedBankAccount linkedBankAccount){
      return linkedBankAccountServices.linkAccount(linkedBankAccount);
    }

    @GetMapping("getLinkedList/{mobNo}")
    public List<linkedAccountDTO> getLinkedList(@PathVariable String mobNo){
        return linkedBankAccountServices.getLinkedListUser(mobNo);
    }

    @GetMapping("get/bank/balance/{id}")
    public List<BalanceCheckDTO> balanceCheck(@PathVariable int id){
        return bankUserServices.checkBalace(id);
    }






}
