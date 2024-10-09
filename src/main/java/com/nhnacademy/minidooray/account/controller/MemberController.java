package com.nhnacademy.minidooray.account.controller;

import com.nhnacademy.minidooray.account.entity.Member;
import com.nhnacademy.minidooray.account.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/member/{memberId}")
    public Member getMember(@PathVariable String memberId){
        return memberService.getMember(memberId);
    }
}
