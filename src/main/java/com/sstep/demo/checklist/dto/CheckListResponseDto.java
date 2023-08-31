package com.sstep.demo.checklist.dto;

import com.sstep.demo.photo.dto.PhotoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CheckListResponseDto {
    private long id; //체크 리스트 고유번호
    private String title; //체크 리스트 제목
    private String contents; //체크 리스트 내용
    private String date; //체크 리스트 작성 일자
    private String endDay; //체크 리스트 마감 일자
    private boolean needPhoto; //체크 리스트 사진 필수 여부
    private boolean isComplete; //체크 리스트 완료 여부
    private String memo; //체크 리스트 완료 시 메모
    private Set<PhotoResponseDto> photoResponseDto; //사진 파일
}
