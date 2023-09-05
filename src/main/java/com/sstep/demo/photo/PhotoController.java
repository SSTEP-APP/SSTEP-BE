package com.sstep.demo.photo;

import com.sstep.demo.photo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/photo")
public class PhotoController {
    private final PhotoService photoService;

    //사진 등록
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE) // JSON 형식의 응답을 지정합니다.
    @ResponseBody
    public ResponseEntity<Long> savePhoto(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        Long photoId = photoService.savePhoto(multipartFile);
        return ResponseEntity.ok(photoId);
    }

}
