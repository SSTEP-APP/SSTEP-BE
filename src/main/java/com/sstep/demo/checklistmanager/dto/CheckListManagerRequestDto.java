package com.sstep.demo.checklistmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CheckListManagerRequestDto {
    private long id; //체크 리스트 담당자 고유번호
    private long staffId; //직원 고유 번호
    private String name; //체크 리스트 담당자 이름
    private String phoneNum; //체크 리스트 담당자 연락처
}
