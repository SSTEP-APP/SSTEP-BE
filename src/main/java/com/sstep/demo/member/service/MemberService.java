package com.sstep.demo.member.service;

import com.sstep.demo.member.MemberMapper;
import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Member getEntity(String memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new);
    }

    public MemberResponseDto findByMemberId(String memberId) {
        return memberMapper.EntityToResponseDto(getEntity(memberId));
    }

    public void save(MemberRequestDto member) {
        String memberId = member.getMemberId();
        if (!isMemberIdDuplicate(memberId)) {
            memberRepository.save(memberMapper.toEntity(member));
        } else {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public boolean isMemberIdDuplicate(String memberId) {
        Optional<Member> findMember = memberRepository.findByMemberId(memberId);
        return findMember.isPresent();
    }
}
