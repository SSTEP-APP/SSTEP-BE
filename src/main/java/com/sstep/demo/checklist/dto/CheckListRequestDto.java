package com.sstep.demo.checklist.dto;

import com.sstep.demo.category.dto.CategoryRequestDto;
import com.sstep.demo.checklistmanager.dto.CheckListManagerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CheckListRequestDto {
    private long id; //체크 리스트 고유번호
    private String title; //체크 리스트 제목
    private String contents; //체크 리스트 내용
    private String endDay; //체크 리스트 마감 일자
    private boolean needPhoto; //체크 리스트 사진 필수 여부
    private boolean isComplete; //체크 리스트 완료 여부
    private String memo; //체크 리스트 완료 시 메모
    private MultipartFile[] multipartFiles; //사진 배열
    private Set<CategoryRequestDto> categoryRequestDto; //카테고리 배열
    private Set<CheckListManagerRequestDto> checkListManagersRequestDto; //체크 리스트 담당자 배열
}
