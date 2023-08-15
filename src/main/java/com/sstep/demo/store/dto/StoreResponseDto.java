package com.sstep.demo.store.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StoreResponseDto {
    private long id; //사업장 고유번호
    private String name; //사업장 이름
    private String address; //사업장 주소
    private String latitude; //사업장 위도 좌표
    private String longitude; //사업장 경도 좌표
    private boolean scale; //사업장 규모(5인이상: T, 이하: F)
    private boolean plan; //사업장 유료플랜 여부
    private long code; //사업장 코드번호 => 인앱 사업장 검색시 사용
    private int count; //사업장 구성원 수

}
