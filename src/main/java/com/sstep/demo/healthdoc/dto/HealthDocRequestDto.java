package com.sstep.demo.healthdoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class HealthDocRequestDto {
    private long id; //문서 고유번호
    private String checkUpDate; //보건증 검진일
    private String expirationDate; //보건증 만료일
    private MultipartFile multipartFile; //사진 정보
}
