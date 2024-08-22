package com.hefshine.phonepayBacendApplication.servicesImp;

import com.hefshine.phonepayBacendApplication.model.BankUser;
import com.hefshine.phonepayBacendApplication.model.LinkedBankAccount;
import com.hefshine.phonepayBacendApplication.model.PhonePayUser;
import com.hefshine.phonepayBacendApplication.model.Transactions;
import com.hefshine.phonepayBacendApplication.model.dto.ResponseVerifyUserDTO;
import com.hefshine.phonepayBacendApplication.model.dto.SendMoneyDTO;
import com.hefshine.phonepayBacendApplication.repo.BankUserRepo;
import com.hefshine.phonepayBacendApplication.repo.LinkedBankAccountRepo;
import com.hefshine.phonepayBacendApplication.repo.PhonePayUserRepo;
import com.hefshine.phonepayBacendApplication.repo.TransactionsRepo;
import com.hefshine.phonepayBacendApplication.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class PaymentServicesImp implements PaymentServices {
    @Autowired
    LinkedBankAccountRepo linkedBankAccountRepo;

    @Autowired
    PhonePayUserRepo phonePayUserRepo;

    @Autowired
    BankUserRepo bankUserRepo;

    @Autowired
    TransactionsRepo transactionsRepo;

    @Override
    public ResponseVerifyUserDTO verifyUPI(String upiId) {
        int count=linkedBankAccountRepo.countByUpiId(upiId);
        if(count>1) return null;
        if(count==1){
            LinkedBankAccount ll=linkedBankAccountRepo.findByUpiId(upiId);
            BankUser bankUser=bankUserRepo.findById(ll.getBankUserId()).get();
            ResponseVerifyUserDTO response=new ResponseVerifyUserDTO();
            response.setUserName(bankUser.getUserName());
            response.setUserBank(bankUser.getBankName());
            return response;
        }
        else{
           int cc=linkedBankAccountRepo.countByMobNo(upiId);
           if(cc==0) return null;
           LinkedBankAccount ll=linkedBankAccountRepo.findByMobNoAndIsDeletedAndIsPrimary(upiId,0,1);
            BankUser bankUser=bankUserRepo.findById(ll.getBankUserId()).get();
            ResponseVerifyUserDTO response=new ResponseVerifyUserDTO();
            response.setUserName(bankUser.getUserName());
            response.setUserBank(bankUser.getBankName());
            return response;

        }
    }


    @Override
    public int sendMoneyDTO(SendMoneyDTO sendMoneyDTO) {
        //check pin
       LinkedBankAccount ll= linkedBankAccountRepo.findByPhonePayUserIdAndIsPrimaryAndIsDeleted(sendMoneyDTO.getSenderId(),1,0);

       System.out.println(sendMoneyDTO.getUpiPin()+"  "+ll.getUpiPin()+" "+sendMoneyDTO.getSenderId());
        if(!Objects.equals(sendMoneyDTO.getUpiPin(), ll.getUpiPin())) return -1;
        // check balance
        BankUser bb=bankUserRepo.findById(ll.getBankUserId()).get();

        if(bb.getBankBalance()<sendMoneyDTO.getSendAmount()) return -2;

        //sender transaction
        Transactions tt=new Transactions();
        tt.setPhonePayUserId(sendMoneyDTO.getSenderId());
        tt.setBankId(bb.getUserId());
        tt.setBalance(bb.getBankBalance());
        tt.setCrdp("Debited");
        tt.setAfterBalance(bb.getBankBalance()-sendMoneyDTO.getSendAmount());
        tt.setCommit("send to "+sendMoneyDTO.getReceiverUpiId());
        tt.setCurrDate(new Date());
        transactionsRepo.save(tt);

        //sender db update
        bb.setBankBalance(tt.getAfterBalance());
        bankUserRepo.save(bb);

        //receiver Transaction
        int count=linkedBankAccountRepo.countByUpiId(sendMoneyDTO.getReceiverUpiId());
        if(count>1) return -3;
        if(count==1){
            LinkedBankAccount receiver=linkedBankAccountRepo.findByUpiId(sendMoneyDTO.getReceiverUpiId());
            BankUser bankUser=bankUserRepo.findById(receiver.getBankUserId()).get();
            tt=new Transactions();
            tt.setPhonePayUserId(receiver.getPhonePayUserId());
            tt.setBankId(receiver.getBankUserId());
            tt.setCrdp("Credited");
            tt.setBalance(sendMoneyDTO.getSendAmount());
            tt.setAfterBalance(bankUser.getBankBalance()+sendMoneyDTO.getSendAmount());
            tt.setCommit("Recieved to "+ll.getUpiId());
            tt.setCurrDate(new Date());
            transactionsRepo.save(tt);

            bankUser.setBankBalance(tt.getAfterBalance());
            bankUserRepo.save(bankUser);
            return 1;
        }
        else{
            int cc=linkedBankAccountRepo.countByMobNo(sendMoneyDTO.getReceiverUpiId());
            if(cc==0) return -1;
            LinkedBankAccount receiver=linkedBankAccountRepo.findByMobNoAndIsDeletedAndIsPrimary(sendMoneyDTO.getReceiverUpiId(),0,1);
            BankUser bankUser=bankUserRepo.findById(receiver.getBankUserId()).get();
            tt=new Transactions();
            tt.setPhonePayUserId(receiver.getPhonePayUserId());
            tt.setBankId(receiver.getBankUserId());
            tt.setCrdp("Credited");
            tt.setBalance(sendMoneyDTO.getSendAmount());
            tt.setAfterBalance(bankUser.getBankBalance()+sendMoneyDTO.getSendAmount());
            tt.setCommit("Recieved to "+ll.getUpiId());
            tt.setCurrDate(new Date());
            transactionsRepo.save(tt);
            bankUser.setBankBalance(tt.getAfterBalance());
            bankUserRepo.save(bankUser);
           return 1;
        }
    }
}
