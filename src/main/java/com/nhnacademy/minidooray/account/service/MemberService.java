package com.nhnacademy.minidooray.account.service;

import com.nhnacademy.minidooray.account.dto.LoginDto;
import com.nhnacademy.minidooray.account.entity.Member;
import com.nhnacademy.minidooray.account.exception.LoginFailedException;
import com.nhnacademy.minidooray.account.exception.MemberNotFoundException;
import com.nhnacademy.minidooray.account.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public Member doLogin(LoginDto dto){
        Optional<Member> optional=memberRepository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new MemberNotFoundException("Member is Not found");
        }

        Member member=optional.get();
        if(!member.getMemberPassword().equals(dto.getPassword())){
            throw new LoginFailedException("Login failed.");
        }

        return member;
    }

    // TODO: 회원가입

}
