package com.sstep.demo.healthdoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class HealthDocRequestDto {
    private String checkUpDate; //보건증 검진일
    private String expirationDate; //보건증 만료일
    private long photoId; //사진 고유 번호
}
