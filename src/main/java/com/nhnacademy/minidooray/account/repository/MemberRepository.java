package com.nhnacademy.minidooray.account.repository;

import com.nhnacademy.minidooray.account.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
}
