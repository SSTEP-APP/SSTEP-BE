package com.sstep.demo.workdoc;

import com.sstep.demo.workdoc.dto.WorkDocResponseDto;
import com.sstep.demo.workdoc.service.WorkDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/work-doc")
public class WorkDocController {
    private final WorkDocService workDocService;

    //근로 계약서 작성에 필요한 정보 가져오기
    @GetMapping("/{storeId}/{staffId}/info")
    public WorkDocResponseDto getInfoForWorkDoc(@PathVariable Long staffId, @PathVariable Long storeId) {
        return workDocService.getInfoForWorkDoc(storeId, staffId);
    }

    //근로 계약서 1차 등록
    @PostMapping("/{staffId}/add/first")
    public ResponseEntity<Void> registerWorkDocFirst(@PathVariable Long staffId, @RequestBody MultipartFile multipartFile) throws IOException {
        workDocService.saveFirst(staffId, multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //근로 계약서 2차(최종) 등록
    @PostMapping("/{staffId}/add/second")
    public ResponseEntity<Void> registerWorkDocSecond(@PathVariable Long staffId, @RequestBody MultipartFile multipartFile) throws IOException {
        workDocService.saveSecond(staffId, multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
