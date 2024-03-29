package com.sstep.demo.staff;

import com.sstep.demo.staff.dto.StaffInviteResponseDto;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.staff.dto.StaffResponseDto;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    //직원 조회
    @GetMapping("/{staffId}")
    public StaffResponseDto getStaffByStaffId(@PathVariable Long staffId) {
        return staffService.getStaff(staffId);
    }

    //직원 정보 입력 시 직원 등록 => null로 저장되어있던 직원 정보를 입력된 정보로 update 과정
    @PostMapping("/{staffId}/update-staff")
    public ResponseEntity<Void> updateStaff(@PathVariable Long staffId, @RequestBody StaffRequestDto staffRequestDto) {
        staffService.updateStaff(staffId, staffRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //아이디와 매장 고유 번호로 직원 고유 번호 가져오기
    @GetMapping("/{username}/{storeCode}")
    public StaffInviteResponseDto getStaffByUsernameAndStoreCode(@PathVariable String username, @PathVariable Long storeCode) {
        return staffService.getStaffByUsernameAndStoreCode(username,storeCode);
    }
}
