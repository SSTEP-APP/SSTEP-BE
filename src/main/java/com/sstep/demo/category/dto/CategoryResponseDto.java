package com.sstep.demo.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CategoryResponseDto {
    private long id; //카테고리 고유번호
    private String name; //카테고리 명


}
