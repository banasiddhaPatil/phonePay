package com.hefshine.phonepayBacendApplication.servicesImp;

import com.hefshine.phonepayBacendApplication.model.BankUser;
import com.hefshine.phonepayBacendApplication.model.PhonePayUser;
import com.hefshine.phonepayBacendApplication.repo.BankUserRepo;
import com.hefshine.phonepayBacendApplication.repo.PhonePayUserRepo;
import com.hefshine.phonepayBacendApplication.services.PhonePayUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.random;

@Service
public class PhonePayUserServicesImp implements PhonePayUserServices {

    @Autowired
    PhonePayUserRepo phonePayUserRepo;

    @Autowired
    BankUserRepo bankUserRepo;

    @Override
    public boolean userRegister(String mobileNumber) {
      int count=bankUserRepo.countByUserMobNumber(mobileNumber);
      int count1=phonePayUserRepo.countByMobNumber(mobileNumber);
      if(count1!=0) return false;
      else if(count>0){
          List<BankUser> bankUser= bankUserRepo.findByUserMobNumber(mobileNumber);
          BankUser newBankUser=bankUser.get(0);
          PhonePayUser phonePayUser=new PhonePayUser();
          phonePayUser.setName(newBankUser.getUserName());
          phonePayUser.setMobNumber(newBankUser.getUserMobNumber());
          Random random=new Random();
          int otp=1000+random.nextInt(9000);
          phonePayUser.setOtp(otp);
          phonePayUserRepo.save(phonePayUser);
          System.out.println(otp);
      }
      else{
          PhonePayUser phonePayUser=new PhonePayUser();
          phonePayUser.setName("user");
          phonePayUser.setMobNumber(mobileNumber);
          Random random=new Random();
          int otp=1000+random.nextInt(9000);
          phonePayUser.setOtp(otp);
          phonePayUserRepo.save(phonePayUser);
          System.out.println(otp);
      }

        return true;
    }

    @Override
    public boolean verifyOTP(String mobNo,int otp) {
        PhonePayUser phonePayUser=phonePayUserRepo.findByMobNumber(mobNo);
        if(phonePayUser==null) return false;
        if(phonePayUser.getOtp().equals(otp)) return true;
        return false;
    }

    @Override
    public int setPassword(String[] str) {
        PhonePayUser phonePayUser=phonePayUserRepo.findByMobNumber(str[0]);
        if(phonePayUser==null) return -1;
        phonePayUser.setPassword(str[1]);
        phonePayUser.setUpiPin(Integer.valueOf(str[2]));
        phonePayUserRepo.save(phonePayUser);
        return phonePayUser.getId();
    }

    @Override
    public int signIn(String mobileNo, String password) {
        int count=phonePayUserRepo.countByMobNumber(mobileNo);
        if(count!=1) return -1;
        PhonePayUser phonePayUser=phonePayUserRepo.findByMobNumber(mobileNo);
        if(!Objects.equals(phonePayUser.getPassword(), password))  return -1;
        return phonePayUser.getId();
    }

    @Override
    public PhonePayUser getPhonePayUser(int id) {
        int count=phonePayUserRepo.countById(id);
        if(count==0) return null;
        return phonePayUserRepo.findById(id).get();
    }
}
