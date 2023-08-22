package com.sstep.demo.workdoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class WorkDocResponseDto {
    private long staffId; //직원 고유 번호
    private String staffName; //직원명
    private String storeName; //사업체 명 => Store
    private String storeAddress; //사업체 주소 => Store
    private String storePhoneNum; //사업체 연락처 => Member
    private String storeOwnerName; //사업체 대표자 이름 => Member
    private LocalDate startDay; //입사일 => Staff
    private int hourMoney; //시급 => Staff
    private int wageType; //급여 지급 방식 => Staff
    private String paymentDate; //급여지급일 => Staff
}
