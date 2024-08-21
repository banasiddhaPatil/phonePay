package com.hefshine.phonepayBacendApplication.repo;

import com.hefshine.phonepayBacendApplication.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankUserRepo extends JpaRepository<BankUser,Integer> {

    public List<BankUser> findByUserMobNumber(String userMobNumber);

    int countByUserMobNumber(String mobileNumber);
}
