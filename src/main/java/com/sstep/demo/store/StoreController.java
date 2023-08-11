package com.sstep.demo.store;

import com.sstep.demo.store.dto.StoreRegisterReqDto;
import com.sstep.demo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    //사업장 등록 => 등록한 사람은 바로 직원으로 추가, 사장으로 취급
    @PostMapping("/register")
    public ResponseEntity<Void> registerStore(@RequestBody StoreRegisterReqDto dto) throws Exception{
        storeService.saveStore(dto); //사업장 등록 로직
        storeService.addOwnerToStore(dto);//사업장 등록한 사람을 사장으로 취급하는 로직
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* //직원 목록 조회
    @GetMapping("/{storeId}/staffs")
    public List<Staff> getStaffsByStoreId(@PathVariable Long storeId) {
        return storeService.getStaffsByStoreId(storeId);
    }

    //직원 추가 => 사업장 코드 입력 후 사장이 승인을 받아줬을 경우
    @PostMapping("/{code}/add/staff")
    public ResponseEntity<Void> addStaffToStore(@PathVariable Long code, @RequestBody MemberRequestDto memberRequestDto, @RequestBody StaffRequestDto staffRequestDto) {
        storeService.addMemberToStore(code, staffRequestDto, memberRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //합류 여부가 false인 직원 리스트 가져오기
    @GetMapping("/{storeId}/unregister-staffs")
    public List<Staff> getUnRegStaffs(@PathVariable Long storeId) {
        return storeService.getUnRegStaffs(storeId);
    }

    //해당 날짜에 근무하는 직원 리스트 가져오기
    @GetMapping("/{storeId}/day-work-staffs")
    public List<Staff> getDayWorkStaffs(@PathVariable Long storeId, @RequestBody CalendarRequestDto calendarRequestDto) {
        return storeService.getDayWorkStaffs(storeId, calendarRequestDto);
    }

    //이의 신청한 직원 리스트 가져오기
    @GetMapping("/{storeId}/dispute-staffs")
    public List<Staff> getDisputeStaffs(@PathVariable Long storeId) {
        return storeService.getDisputeStaffs(storeId);
    }

    //사업장 내 전체 공지사항 목록 조회
    @GetMapping("/{storeId}/notices")
    public List<Notice> getNotices(@PathVariable Long storeId) {
        return storeService.getNotices(storeId);
    }*/


}
