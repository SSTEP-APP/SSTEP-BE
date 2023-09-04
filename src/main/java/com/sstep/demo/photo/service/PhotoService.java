package com.sstep.demo.photo.service;

import com.sstep.demo.photo.PhotoRepository;
import com.sstep.demo.photo.domain.Photo;
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

    public ResponseEntity<Long> savePhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

        Photo newPhoto = Photo.builder()
                .data(data)
                .fileName(fileName)
                .contentType(contentType)
                .build();

        photoRepository.save(newPhoto);

        return new ResponseEntity<>(newPhoto.getId(), HttpStatus.OK);
    }

}
