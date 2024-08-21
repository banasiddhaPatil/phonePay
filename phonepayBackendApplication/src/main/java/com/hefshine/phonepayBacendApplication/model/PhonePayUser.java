package com.hefshine.phonepayBacendApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@AllArgsConstructor@NoArgsConstructor@Setter@Getter
public class PhonePayUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String mobNumber;
    private String password;
    private Integer upiPin;
    private Integer otp;
}
