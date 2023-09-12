package com.sstep.demo.workdoc;

import com.sstep.demo.photo.dto.PhotoResponseDto;
import com.sstep.demo.workdoc.dto.WorkDocRequestDto;
import com.sstep.demo.workdoc.dto.WorkDocResponseDto;
import com.sstep.demo.workdoc.service.WorkDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    public ResponseEntity<Void> registerWorkDocFirst(@PathVariable Long staffId, @RequestBody WorkDocRequestDto workDocRequestDto) {
        workDocService.saveFirst(staffId, workDocRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //1차 등록한 계약서(사진) 정보
    @GetMapping("/{staffId}/first")
    public PhotoResponseDto getFirstWorkDoc(@PathVariable Long staffId) {
        return workDocService.getFirstWorkDoc(staffId);
    }

    //근로 계약서 2차(최종) 등록
    @PostMapping("/{staffId}/add/second")
    public ResponseEntity<Void> registerWorkDocSecond(@PathVariable Long staffId, @RequestBody WorkDocRequestDto workDocRequestDto) {
        workDocService.saveSecond(staffId, workDocRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //근로 계약서 최종 정보
    @GetMapping("/{staffId}/second")
    public PhotoResponseDto getSecondWorkDoc(@PathVariable Long staffId) {
        return workDocService.getSecondWorkDoc(staffId);
    }

    //근로 계약서 1차 등록한 직원 목록
    @GetMapping("/{storeId}/reg-first/work-doc/staffs")
    public Set<WorkDocResponseDto> getRegFirstWorkDocStaffs(@PathVariable Long storeId) {
        return workDocService.getRegFirstWorkDocStaffs(storeId);
    }

    //근로 계약서 2차 등록한 직원 목록
    @GetMapping("/{storeId}/reg-second/work-doc/staffs")
    public Set<WorkDocResponseDto> getRegSecondWorkDocStaffs(@PathVariable Long storeId) {
        return workDocService.getRegSecondWorkDocStaffs(storeId);
    }

    //근로 계약서 미 등록한 직원 목록
    @GetMapping("/{storeId}/un-reg/work-doc/staffs")
    public Set<WorkDocResponseDto> getUnRegWorkDocStaffs(@PathVariable Long storeId) {
        return workDocService.getUnRegWorkDocStaffs(storeId);
    }
}
