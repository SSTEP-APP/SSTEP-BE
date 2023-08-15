package com.sstep.demo.store;

import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffInviteResponseDto;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.staff.dto.StaffResponseDto;
import com.sstep.demo.store.dto.StoreRegisterReqDto;
import com.sstep.demo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    //사업장 등록 => 등록한 사람은 바로 직원으로 추가, 사장으로 취급
    @PostMapping("/register")
    public ResponseEntity<Void> registerStore(@RequestBody StoreRegisterReqDto dto) {
        storeService.saveStore(dto); //사업장 등록 로직
        storeService.addOwnerToStore(dto);//사업장 등록한 사람을 사장으로 취급하는 로직
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //직원 목록 조회
    @GetMapping("/{storeId}/staffs")
    public Set<Staff> getStaffsByStoreId(@PathVariable Long storeId) {
        return storeService.getStaffsByStoreId(storeId);
    }

    //직원 조회
    @GetMapping("/{staffId}")
    public StaffResponseDto getStaffByStaffId(@PathVariable Long staffId) {
        return storeService.getStaffByStaffId(staffId);
    }

    //직원 초대 => 사업장 코드 전송
    @PostMapping("/invite/staff")
    public ResponseEntity<Void> inviteStaffToStore(@RequestBody StaffRequestDto dto) {
        storeService.inviteMemberToStore(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //직원이 사업장 코드 입력시
    @PostMapping("/input-code/staff")
    public ResponseEntity<Void> inputCode(@RequestBody StaffRequestDto dto) {
        storeService.inputCode(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //직원 추가 => 사업장 코드 입력 후 사장이 승인을 받아줬을 경우
    @PostMapping("/add/staff")
    public ResponseEntity<Void> addStaffToStore(@RequestBody StaffRequestDto dto) {
        storeService.addMemberToStore(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //초대 여부가 true 직원 리스트 가져오기
    @GetMapping("/{storeId}/invite-staffs")
    public Set<StaffInviteResponseDto> getInviteStaffs(@PathVariable Long storeId) {
        return storeService.getInviteStaffs(storeId);
    }

    //코드 입력 여부가 true인 직원 리스트 가져오기
    @GetMapping("/{storeId}/input-code/staffs")
    public Set<StaffInviteResponseDto> getInputCodeStaffs(@PathVariable Long storeId) {
        return storeService.getInputCodeStaffs(storeId);
    }

    //해당 날짜에 근무하는 직원 리스트 가져오기
    @GetMapping("/{storeId}/day-work-staffs")
    public Set<Staff> getDayWorkStaffs(@PathVariable Long storeId, @RequestBody CalendarRequestDto calendarRequestDto) {
        return storeService.getDayWorkStaffs(storeId, calendarRequestDto);
    }

    //이의 신청한 직원 리스트 가져오기
    @GetMapping("/{storeId}/dispute-staffs")
    public Set<Staff> getDisputeStaffs(@PathVariable Long storeId) {
        return storeService.getDisputeStaffs(storeId);
    }

    //사업장 내 전체 공지사항 목록 조회
    @GetMapping("/{storeId}/notices")
    public Set<Notice> getNotices(@PathVariable Long storeId) {
        return storeService.getNotices(storeId);
    }


}
