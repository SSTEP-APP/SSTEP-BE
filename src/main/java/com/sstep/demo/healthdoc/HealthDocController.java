package com.sstep.demo.healthdoc;

import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.healthdoc.dto.HealthDocRequestDto;
import com.sstep.demo.healthdoc.service.HealthDocService;
import com.sstep.demo.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/health-doc")
public class HealthDocController {
    private final HealthDocService healthDocService;

    //보건증 등록
//    @PostMapping("/{staffId}/add")
//    public ResponseEntity<Void> registerHealthDoc(@PathVariable Long staffId, @RequestBody HealthDocRequestDto healthDocRequestDto
//            , @RequestBody MultipartFile multipartFile) throws IOException {
//        healthDocService.saveHealthDoc(staffId, healthDocRequestDto, multipartFile);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    //직원별 보건증 상세 정보 조회
    @GetMapping("/{staffId}/detail")
    public HealthDoc getHealthDoc(@PathVariable Long staffId) {
        return healthDocService.getHealthDoc(staffId);
    }

    //보건증을 등록한 직원 목록
    @GetMapping("/{storeId}/reg/health-doc/staffs")
    public Set<Staff> getRegHealthDocStaffs(@PathVariable Long storeId) {
        return healthDocService.getRegHealthDocStaffs(storeId);
    }

    //보건증을 미 등록한 직원 목록
    @GetMapping("/{storeId}/un-reg/health-doc/staffs")
    public Set<Staff> getUnRegHealthDocStaffs(@PathVariable Long storeId) {
        return healthDocService.getUnRegHealthDocStaffs(storeId);
    }
}
