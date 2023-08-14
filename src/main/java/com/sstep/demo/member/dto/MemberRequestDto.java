package com.sstep.demo.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberRequestDto {
    private String name;

    private String phoneNum;

    private String username;

    private String password;
}
