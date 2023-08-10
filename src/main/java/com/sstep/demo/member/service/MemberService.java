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

    public void save(MemberRequestDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getUsername())
                .name(memberDto.getName())
                .phoneNum(memberDto.getPhoneNum())
                .password(memberDto.getPassword())
                .staffList(new ArrayList<>())
                .build();

        memberRepository.save(member);
    }

    public boolean isMemberIdDuplicate(MemberRequestDto memberRequestDto) {
        Optional<Member> findMember = memberRepository.findByUsername(memberRequestDto.getUsername());
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

    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow();
    }
}
