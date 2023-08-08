package com.sstep.demo.healthdoc.dto;

import java.time.LocalDate;

public class HealthDocRequestDto {
    private long id; //문서 고유번호
    private LocalDate checkUpDate; //보건증 검진일
    private LocalDate expirationDate; //보건증 만료일
}
