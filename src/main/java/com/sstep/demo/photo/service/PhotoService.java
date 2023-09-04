package com.sstep.demo.photo.service;

import com.sstep.demo.photo.PhotoRepository;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public ResponseEntity<PhotoResponseDto> savePhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

        Photo newPhoto = Photo.builder()
                .data(data)
                .fileName(fileName)
                .contentType(contentType)
                .build();

        photoRepository.save(newPhoto);

        PhotoResponseDto photoResponseDto = PhotoResponseDto.builder()
                .id(newPhoto.getId())
                .fileName(newPhoto.getFileName())
                .contentType(newPhoto.getContentType())
                .data(newPhoto.getData())
                .build();

        return new ResponseEntity<>(photoResponseDto, HttpStatus.OK);
    }

}
