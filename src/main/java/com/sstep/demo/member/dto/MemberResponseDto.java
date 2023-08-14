package com.sstep.demo.member.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberResponseDto {
    private String username;

    private String name;

    private String phoneNum;

    private String password;

}
