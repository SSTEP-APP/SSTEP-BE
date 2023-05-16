package com.sstep.demo.member.service;

import com.sstep.demo.member.MemberMapper;
import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.dto.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberJoinDto findByMemberId(String memberId) {
        return memberMapper.toJoinDto(memberRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new));
    }
}
