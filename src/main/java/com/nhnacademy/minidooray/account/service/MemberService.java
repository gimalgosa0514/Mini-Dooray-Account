package com.nhnacademy.minidooray.account.service;

import com.nhnacademy.minidooray.account.dto.LoginDto;
import com.nhnacademy.minidooray.account.dto.MemberDto;
import com.nhnacademy.minidooray.account.entity.Member;
import com.nhnacademy.minidooray.account.entity.MemberStatus;
import com.nhnacademy.minidooray.account.exception.LoginFailedException;
import com.nhnacademy.minidooray.account.exception.MemberAlreadyExistsException;
import com.nhnacademy.minidooray.account.exception.MemberNotFoundException;
import com.nhnacademy.minidooray.account.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public MemberDto getMember(String memberId){

        if (memberId == null || memberId.isEmpty()){
            throw new IllegalArgumentException("Member id cannot be null or empty");
        }

        Optional<Member> optional = memberRepository.findById(memberId);

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


    public void registerMember(MemberDto dto){
        if (checkParameterIllegal(dto)){
            throw new IllegalArgumentException("Member cannot be empty");
        }

        if (existsMember(dto.getId())){
            throw new MemberAlreadyExistsException("Member already exists");
        }
        Member member = new Member(dto.getId(),dto.getPassword(),dto.getEmail(), MemberStatus.SIGNED);
        memberRepository.save(member);
    }

    public boolean existsMember(String memberId){
        return memberRepository.existsById(memberId);
    }

    public boolean checkParameterIllegal(MemberDto dto){
        return Objects.isNull(dto) || dto.getEmail() == null || dto.getPassword() == null
                || dto.getEmail().isEmpty() || dto.getPassword().isEmpty() || dto.getId() == null
                || dto.getId().isEmpty();
    }
}
