package com.hefshine.phonepayBacendApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class LinkedBankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer phonePayUserId;
    private Integer bankUserId;
    private String mobNo;
    private Integer upiPin;
    private String upiId;
    private Integer isDeleted;
    private int isPrimary;
    private int otp;


}
