package com.sstep.demo.checklistmanager;

import com.sstep.demo.checklistmanager.dto.CheckListManagerRequestDto;
import com.sstep.demo.checklistmanager.service.CheckListManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/check-manager")
public class CheckListManagerController {
    private final CheckListManagerService checkListManagerService;

    @PostMapping("/{checkListId}/add")
    public ResponseEntity<Void> registerCheckListManager(@PathVariable Long checkListId, @RequestBody Set<CheckListManagerRequestDto> checkListManagerRequestDto) {
        checkListManagerService.saveManagers(checkListId, checkListManagerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
