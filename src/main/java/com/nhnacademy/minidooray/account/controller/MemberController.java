package com.nhnacademy.minidooray.account.controller;

import com.nhnacademy.minidooray.account.dto.MemberDto;
import com.nhnacademy.minidooray.account.dto.MessageDto;
import com.nhnacademy.minidooray.account.exception.MemberAlreadyExistsException;
import com.nhnacademy.minidooray.account.exception.MemberNotFoundException;
import com.nhnacademy.minidooray.account.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/api/member/{memberId}")
    public ResponseEntity<MemberDto> getMember(@PathVariable String memberId){
        return new ResponseEntity<>(memberService.getMember(memberId),HttpStatus.OK);
    }

    @PostMapping("/api/member")
    public ResponseEntity<MessageDto> registerMember(@RequestBody MemberDto dto){
        memberService.registerMember(dto);

        return new ResponseEntity<>(new MessageDto("저장 성공"), HttpStatus.CREATED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageDto> handleException(IllegalArgumentException ex){
        return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MemberAlreadyExistsException.class)
    public ResponseEntity<MessageDto> handleException(MemberAlreadyExistsException ex){
        return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<MessageDto> handleException(MemberNotFoundException ex){
        return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
