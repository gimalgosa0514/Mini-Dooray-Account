package com.nhnacademy.minidooray.account.controller;

import com.nhnacademy.minidooray.account.dto.MemberDto;
import com.nhnacademy.minidooray.account.entity.Member;
import com.nhnacademy.minidooray.account.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/member")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto dto){
        memberService.registerMember(dto);
        return ResponseEntity.ok().build();
    }
}
