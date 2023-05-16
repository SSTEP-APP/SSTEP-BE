package com.sstep.demo.member;

import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberJoinDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberJoinDto toJoinDto(Member member);
}
