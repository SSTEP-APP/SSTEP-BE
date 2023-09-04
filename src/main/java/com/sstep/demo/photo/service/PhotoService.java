package com.sstep.demo.photo.service;

import com.sstep.demo.photo.PhotoRepository;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoResponseDto savePhoto(MultipartFile file) throws IOException {
        if (file == null) {
            throw new FileUploadException();
        }

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

        Photo newPhoto = Photo.builder()
                .data(data)
                .fileName(fileName)
                .contentType(contentType)
                .build();

        photoRepository.save(newPhoto);

        Photo findFile = photoRepository.findByFileName(fileName);

        return PhotoResponseDto.builder()
                .id(findFile.getId())
                .fileName(findFile.getFileName())
                .contentType(findFile.getContentType())
                .data(findFile.getData())
                .build();
    }
}
