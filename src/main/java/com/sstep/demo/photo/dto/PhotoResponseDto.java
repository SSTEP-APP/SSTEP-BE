package com.sstep.demo.photo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PhotoResponseDto {
    private long id;
    private String fileName;
    private String contentType;
    private byte[] data;
}
