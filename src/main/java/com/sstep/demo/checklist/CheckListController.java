package com.sstep.demo.checklist;

import com.sstep.demo.category.dto.CategoryRequestDto;
import com.sstep.demo.checklist.dto.CheckListRequestDto;
import com.sstep.demo.checklist.dto.CheckListResponseDto;
import com.sstep.demo.checklist.service.CheckListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/check-list")
public class CheckListController {
    private final CheckListService checkListService;

    //    체크 리스트 작성
    @PostMapping("/{staffId}/add")
    public ResponseEntity<Void> registerCheckList(@PathVariable Long staffId, @RequestBody CheckListRequestDto checkListRequestDto) throws IOException {
        checkListService.saveCheckList(staffId, checkListRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //카테고리 별 체크 리스트 완료 목록
    @GetMapping("/{storeId}/complete-checklists")
    public Set<CheckListResponseDto> getCompleteCheckListsByCategory(@PathVariable Long storeId, @RequestBody CategoryRequestDto categoryRequestDto) {
        return checkListService.getCompleteCheckListsByCategory(storeId, categoryRequestDto);
    }

    //카테고리 별 체크 리스트 미완료 목록
    @GetMapping("/{storeId}/uncompleted-checklists")
    public Set<CheckListResponseDto> getUnCompletedCheckListsByCategory(@PathVariable Long storeId, @RequestBody CategoryRequestDto categoryRequestDto) {
        return checkListService.getUnCompletedCheckListsByCategory(storeId, categoryRequestDto);
    }

    //체크 리스트 상세 조회
    @GetMapping("/{checklistId}/detail")
    public CheckListResponseDto getCheckList(@PathVariable Long checklistId) {
        return checkListService.getCheckList(checklistId);
    }

    //체크 리스트 완료 처리
    @PostMapping("/{staffId}/{checklistId}/complete")
    public ResponseEntity<Void> completeCheckList(@PathVariable Long staffId, @PathVariable Long checklistId, @RequestBody CheckListRequestDto checkListRequestDto) throws IOException {
        checkListService.completeCheckList(staffId, checklistId, checkListRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
