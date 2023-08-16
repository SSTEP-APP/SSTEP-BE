package com.sstep.demo.staff;

import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.commute.dto.CommuteRequestDto;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.schedule.dto.ScheduleRequestDto;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.staff.dto.StaffResponseDto;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

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

    //공지사항 등록
    @PostMapping("/{staffId}/add-notice")
    public ResponseEntity<Void> registerNotice(@PathVariable Long staffId,
                                               @RequestBody NoticeRequestDto noticeRequestDto) throws IOException {
        staffService.saveNotice(staffId, noticeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //보건증을 등록한 직원 목록
    @GetMapping("/{storeId}/reg/health-doc/staffs")
    public Set<Staff> getRegHealthDocStaffs(@PathVariable Long storeId) {
        return staffService.getRegHealthDocStaffs(storeId);
    }

    //보건증을 미 등록한 직원 목록
    @GetMapping("/{storeId}/un-reg/health-doc/staffs")
    public Set<Staff> getUnRegHealthDocStaffs(@PathVariable Long storeId) {
        return staffService.getUnRegHealthDocStaffs(storeId);
    }

    //근로 계약서 등록한 직원 목록
    @GetMapping("/{storeId}/reg/work-doc/staffs")
    public Set<Staff> getRegWorkDocStaffs(@PathVariable Long storeId) {
        return staffService.getRegWorkDocStaffs(storeId);
    }


    //근로 계약서 미 등록한 직원 목록
    @GetMapping("/{storeId}/un-reg/work-doc/staffs")
    public Set<Staff> getUnRegWorkDocStaffs(@PathVariable Long storeId) {
        return staffService.getUnRegWorkDocStaffs(storeId);
    }
}
