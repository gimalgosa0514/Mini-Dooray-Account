package com.nhnacademy.minidooray.account.controller;

import com.nhnacademy.minidooray.account.dto.MemberDto;
import com.nhnacademy.minidooray.account.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<MemberDto> getMember(@PathVariable String memberId){
        return new ResponseEntity<>(memberService.getMember(memberId),HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto dto){
        memberService.registerMember(dto);

        return new ResponseEntity<>("저장성공", HttpStatus.OK);
    }
}
