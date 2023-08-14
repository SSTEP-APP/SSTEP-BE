package com.sstep.demo.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StaffInviteResponseDto {
    private String name; //회원 이름
    private String username; //회원 아이디
}
