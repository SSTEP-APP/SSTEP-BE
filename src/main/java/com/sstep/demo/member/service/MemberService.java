package com.sstep.demo.member.service;

import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.member.dto.MemberResponseDto;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public boolean isUsernameDuplicate(MemberRequestDto memberRequestDto) {
        return memberRepository.findByUsername(memberRequestDto.getUsername()) != null;
    }

    public List<Store> getStoresBelongMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Store> stores = new ArrayList<>();
        for (Staff staff : member.getStaffList()) {
            stores.add(staff.getStore());
        }
        return stores;
    }

    public MemberResponseDto getMemberByUsername(String username) {
        Member findMember = memberRepository.findByUsername(username);
        MemberResponseDto member = MemberResponseDto.builder()
                .name(findMember.getName())
                .username(findMember.getUsername())
                .password(findMember.getPassword())
                .phoneNum(findMember.getPhoneNum())
                .build();
        return member;
    }
}
