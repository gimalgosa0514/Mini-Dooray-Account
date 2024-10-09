package com.nhnacademy.minidooray.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id
    private String memberId;
    private String memberPassword;
    private String memberEmail;
}
