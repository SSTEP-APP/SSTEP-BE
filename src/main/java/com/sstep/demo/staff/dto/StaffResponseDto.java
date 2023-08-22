package com.sstep.demo.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StaffResponseDto {
    private long id; //직원 고유 번호
    private LocalDate startDay; //입사일
    private int hourMoney; //시급
    private int wageType; //급여 지급 방식 일급(1), 주급(2), 월급(3)
    private String paymentDate; //급여지급일
    private boolean ownerStatus; //사장 여부
    private boolean joinStatus; //초대 여부, 초대를 보냈으면 true
    private boolean submitStatus; //코드 입력 여부, 코드를 입력했으면 true
}

