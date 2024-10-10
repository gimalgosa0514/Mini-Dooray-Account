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

    public MemberDto getMember(String memberId){
        Optional<Member> optional=memberRepository.findById(memberId);
        if(optional.isEmpty()){
            throw new MemberNotFoundException("Member is Not found");
        }

        Member member=optional.get();
        MemberDto dto=new MemberDto();
        dto.setId(member.getMemberId());
        dto.setPassword(member.getMemberPassword());
        dto.setEmail(member.getMemberEmail());
        return dto;
    }

    public Member doLogin(LoginDto dto){
        Optional<Member> optional=memberRepository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new MemberNotFoundException("Member is Not found.");
        }

        Member member=optional.get();
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
