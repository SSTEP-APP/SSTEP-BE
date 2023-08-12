package com.sstep.demo.member.service;

import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.member.dto.MemberResponseDto;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<StoreResponseDto> getStoresBelongMember(String username) {
        Member member = memberRepository.findByUsername(username);
        List<StoreResponseDto> stores = new ArrayList<>();
        for (Staff staff : member.getStaffList()) {
            StoreResponseDto store = StoreResponseDto.builder()
                    .name(staff.getStore().getName())
                    .address(staff.getStore().getAddress())
                    .count(staff.getStore().getStaffList().size()).build();

            stores.add(store);
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
