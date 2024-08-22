package com.hefshine.phonepayBacendApplication.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVerifyUserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String userName;
    String userBank;
}
