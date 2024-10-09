package com.nhnacademy.minidooray.account.controller;

import com.nhnacademy.minidooray.account.dto.LoginDto;
import com.nhnacademy.minidooray.account.entity.Member;
import com.nhnacademy.minidooray.account.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private MemberService memberService;

    public LoginController(MemberService memberService){
        this.memberService=memberService;
    }

    @PostMapping("/login")
    public Member login(@RequestBody LoginDto dto){
        return memberService.doLogin(dto);
    }
}
