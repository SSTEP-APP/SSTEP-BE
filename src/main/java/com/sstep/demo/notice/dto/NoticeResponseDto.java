package com.sstep.demo.notice.dto;

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
public class NoticeResponseDto {
    private long id; //공지 고유번호
    private String title; //공지글 제목
    private String writeDate; //공지글 작성 일자
    private String contents; //공지글 내용
    private int hits; //공지 조회수
    private Set<PhotoResponseDto> photo; //사진 정보
}
