package com.sstep.demo.member.dto;

import com.sstep.demo.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinDto {
    private long id;
    private String memberId;

    private String name;

    private String phoneNum;

    private String password;

}
