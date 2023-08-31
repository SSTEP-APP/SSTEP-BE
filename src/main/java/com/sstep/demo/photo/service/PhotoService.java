package com.sstep.demo.photo.service;

import com.sstep.demo.photo.PhotoRepository;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoResponseDto savePhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

        Photo newPhoto = new Photo(fileName, contentType, data);
        PhotoResponseDto photo = PhotoResponseDto.builder()
                .id(newPhoto.getId())
                .fileName(newPhoto.getFileName())
                .contentType(newPhoto.getContentType())
                .data(newPhoto.getData())
                .build();

        photoRepository.save(newPhoto);

        return photo;
    }
}
