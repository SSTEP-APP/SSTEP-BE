package com.sstep.demo.healthdoc.dto;

import com.sstep.demo.photo.dto.PhotoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class HealthDocResponseDto {
    private Long id; //문서 고유번호
    private Long staffId; //직원 고유번호
    private String name; //직원명
    private String checkUpDate; //보건증 검진일
    private String expirationDate; //보건증 만료일
    private PhotoResponseDto photoResponseDto; //사진 정보
}
