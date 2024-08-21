package com.hefshine.phonepayBacendApplication.servicesImp;
import com.hefshine.phonepayBacendApplication.model.BankUser;
import com.hefshine.phonepayBacendApplication.model.LinkedBankAccount;
import com.hefshine.phonepayBacendApplication.model.PhonePayUser;
import com.hefshine.phonepayBacendApplication.model.dto.linkedAccountDTO;
import com.hefshine.phonepayBacendApplication.repo.BankUserRepo;
import com.hefshine.phonepayBacendApplication.repo.LinkedBankAccountRepo;
import com.hefshine.phonepayBacendApplication.repo.PhonePayUserRepo;
import com.hefshine.phonepayBacendApplication.services.LinkedBankAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LinkedBankServicesImpl implements LinkedBankAccountServices {

    @Autowired
    BankUserRepo bankUserRepo;

    @Autowired
    LinkedBankAccountRepo linkedBankUserRepo;

    @Autowired
    PhonePayUserRepo phonePayUserRepo;
    @Override
    public int setUser(int id) {

        BankUser bankUser=bankUserRepo.findById(id).get();
        int count=phonePayUserRepo.countByMobNumber(bankUser.getUserMobNumber());
        if(count!=1) return -1;
        PhonePayUser phonePayUser=phonePayUserRepo.findByMobNumber(bankUser.getUserMobNumber());

        int count1=linkedBankUserRepo.countByBankUserId(bankUser.getUserId());
        if(count1==0) {
            LinkedBankAccount linkedBankAccount = new LinkedBankAccount();
            linkedBankAccount.setBankUserId(bankUser.getUserId());
            linkedBankAccount.setPhonePayUserId(phonePayUser.getId());
            linkedBankAccount.setMobNo(phonePayUser.getMobNumber());
            int userCount= linkedBankUserRepo.countByPhonePayUserId(phonePayUser.getId());
            if(userCount==0) {
                linkedBankAccount.setIsPrimary(1);
            }
            Random random = new Random();
            int otp = 1000 + random.nextInt(9000);
            linkedBankAccount.setOtp(otp);
            linkedBankUserRepo.save(linkedBankAccount);
            System.out.println(otp);
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public int linkAccount(LinkedBankAccount linkedBankAccount) {
        int count=linkedBankUserRepo.countByPhonePayUserIdAndBankUserId(linkedBankAccount.getPhonePayUserId(),linkedBankAccount.getBankUserId());
        System.out.println(count);
        if(count ==0 ) return -1;

        else{
            PhonePayUser phonePayUser=phonePayUserRepo.findById(linkedBankAccount.getPhonePayUserId()).get();
            LinkedBankAccount link=linkedBankUserRepo.findByPhonePayUserIdAndBankUserId(linkedBankAccount.getPhonePayUserId(),linkedBankAccount.getBankUserId());
            if(linkedBankAccount.getOtp()!=link.getOtp()) return -2;
           BankUser bankUser=bankUserRepo.findById(linkedBankAccount.getBankUserId()).get();
            link.setUpiId(STR."\{bankUser.getUserMobNumber()}@\{bankUser.getBankName()}.ibl");
             link.setUpiPin(linkedBankAccount.getUpiPin());
             link.setIsDeleted(0);
            linkedBankUserRepo.save(link);
        }
      return 1;
   }

    @Override
    public List<linkedAccountDTO> getLinkedListUser(String mobNo) {
        int count=linkedBankUserRepo.countByMobNo(mobNo);
        if(count==0) return new ArrayList<>();

        List<LinkedBankAccount> list=linkedBankUserRepo.findByMobNo(mobNo);
        List<linkedAccountDTO> ll=new ArrayList<>();
        for(LinkedBankAccount value:list){
            int id= value.getBankUserId();
            BankUser bb=bankUserRepo.findById(id).get();
            linkedAccountDTO response=new linkedAccountDTO();
            response.setUpiId(value.getUpiId());
            response.setBankName(bb.getBankName());
            response.setIfsc(bb.getIfscCode());
            response.setAccountNumber(bb.getAccountNumber());
            ll.add(response);
        }
        return ll;

    }
}
