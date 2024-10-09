package com.nhnacademy.minidooray.account.service;

import com.nhnacademy.minidooray.account.dto.LoginDto;
import com.nhnacademy.minidooray.account.dto.MemberDto;
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

    public Member getMember(String memberId){
        Optional<Member> member=memberRepository.findById(memberId);
        if(member.isEmpty()){
            throw new MemberNotFoundException("Member is Not found");
        }

        return member.get();
    }

    public Member doLogin(LoginDto dto){
        Member member=getMember(dto.getId());
        if(!member.getMemberPassword().equals(dto.getPassword())){
            throw new LoginFailedException("Login failed.");
        }

        return member;
    }

    public void registerMember(MemberDto dto){
        Member member=new Member(dto.getId(),dto.getPassword(),dto.getEmail());
        memberRepository.save(member);
    }

}
