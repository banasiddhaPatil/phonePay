package com.hefshine.phonepayBacendApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    Integer phonePayUserId;
    Integer bankId;
    Double balance;
    Double afterBalance;
    String crdp;
    Date  currDate;
    String commit;

}
