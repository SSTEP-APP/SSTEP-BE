package com.sstep.demo.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CheckListCategoryDto {
    private String date; //체크 리스트 마감 일자
    private long categoryId; //카테고리 고유번호
}
