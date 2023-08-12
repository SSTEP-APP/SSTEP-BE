package com.sstep.demo.store.dto;

import lombok.Builder;

@Builder
public class StoreResponseDto {
    private String name; //사업장 이름
    private String address; //사업장 주소
    private int count; //사업장 구성원 수
}
