package com.sstep.demo.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CheckListRequestDto {
    private long id; //체크 리스트 고유번호
    private String title; //체크 리스트 제목
    private String contents; //체크 리스트 내용
    private Date endDay; //체크 리스트 마감 일자
    private boolean needPhoto; //체크 리스트 사진 필수 여부
    private boolean isComplete; //체크 리스트 완료 여부
    private String memo; //체크 리스트 완료 시 메모
}
