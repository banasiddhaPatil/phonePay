package com.hefshine.phonepayBacendApplication.model.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class SendMoneyDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    Integer senderId;
    Integer senderBankId;
    Double sendAmount;
    Integer upiPin;
    String receiverUpiId;
}
