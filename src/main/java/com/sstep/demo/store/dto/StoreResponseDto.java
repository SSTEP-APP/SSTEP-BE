package com.sstep.demo.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StoreResponseDto {
    @JsonProperty("name")
    private String name;//사업장 이름
    @JsonProperty("address")
    private String address; //사업장 주소
    @JsonProperty("count")
    private int count; //사업장 구성원 수
}
