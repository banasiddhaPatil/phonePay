package com.hefshine.phonepayBacendApplication.servicesImp;

import com.hefshine.phonepayBacendApplication.model.BankUser;
import com.hefshine.phonepayBacendApplication.repo.BankUserRepo;
import com.hefshine.phonepayBacendApplication.repo.LinkedBankAccountRepo;
import com.hefshine.phonepayBacendApplication.services.BankUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankUserServicesImp implements BankUserServices {

    @Autowired
    BankUserRepo bankUserRepo;

    @Autowired
    LinkedBankAccountRepo linkedBankAccountRepo;
    @Override
    public List<BankUser> getAllBankDetails(String mobNo) {
        List<BankUser> list= bankUserRepo.findByUserMobNumber(mobNo);
        List<BankUser> list2=new ArrayList<>();
        for(BankUser bank:list){
            int count=linkedBankAccountRepo.countByBankUserId(bank.getUserId());
            if(count==0) list2.add(bank);
        }
        return list2;
    }
}
