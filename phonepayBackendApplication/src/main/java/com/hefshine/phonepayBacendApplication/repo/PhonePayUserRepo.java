package com.hefshine.phonepayBacendApplication.repo;

import com.hefshine.phonepayBacendApplication.model.PhonePayUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhonePayUserRepo extends JpaRepository<PhonePayUser,Integer> {

    public PhonePayUser findByMobNumber(String mobNumber);
    public int countByMobNumber(String mobNumber);

    int countById(int id);
}
