package com.hefshine.phonepayBacendApplication.repo;

import com.hefshine.phonepayBacendApplication.model.LinkedBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkedBankAccountRepo extends JpaRepository<LinkedBankAccount,Integer> {
    //int countByBankUserIdAndUpiIdAndIsDeleted(Integer userId, Integer i,Integer isDelated);

    int countByPhonePayUserId(Integer id);

    int countByBankUserIdAndOtp(Integer userId, Integer i);

    int countByBankUserId(Integer userId);

    int countByPhonePayUserIdAndBankUserId(Integer phonePayUserId, Integer bankUserId);

   // LinkedBankAccount findByMobNo(String mobNumber);

    LinkedBankAccount findByPhonePayUserIdAndBankUserId(Integer phonePayUserId, Integer bankUserId);

    int countByMobNo(String mobNo);

    List<LinkedBankAccount> findByMobNo(String mob);
    // int countByPhonePayUserIdAndBankUserId(int phonepayId,int bankId);
}
