package com.sstep.demo.member.service;

import com.sstep.demo.member.MemberMapper;
import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.member.dto.MemberResponseDto;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Member getEntity(String memberId) {
        return memberRepository.findByUsername(memberId).orElseThrow(EntityNotFoundException::new);
    }

    public MemberResponseDto getEntityByMemberId(String memberId) {
        return memberMapper.EntityToResponseDto(getEntity(memberId));
    }

    public void save(MemberRequestDto memberDto) {
        String memberId = memberDto.getMemberId();
        if (!isMemberIdDuplicate(memberId)) {
            Member member = memberMapper.toEntity(memberDto);
            member.setStaffList(new ArrayList<>());
            memberRepository.save(member);
        }
    }

    public boolean isMemberIdDuplicate(String memberId) {
        Optional<Member> findMember = memberRepository.findByUsername(memberId);
        return findMember.isPresent();
    }

    public List<Store> getStoresBelongMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Store> stores = new ArrayList<>();
        for (Staff staff : member.getStaffList()) {
            stores.add(staff.getStore());
        }
        return stores;
    }
}
