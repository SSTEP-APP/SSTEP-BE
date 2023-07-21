package com.sstep.demo.staff;

import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.staff.service.StaffService;
import com.sstep.demo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    //직원 정보 입력 시 직원 등록 => null로 저장되어있던 직원 정보를 입력된 정보로 update 과정
    @PostMapping("/{storeId}/{staffId}/updateStaff")
    public ResponseEntity<Void> updateStaff(@PathVariable Long storeId, @PathVariable Long staffId, @RequestBody StaffRequestDto staffRequestDto) {
        staffService.updateStaff(storeId, staffId, staffRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
