package com.sstep.demo.store;

import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.store.dto.StoreRequestDto;
import com.sstep.demo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    //사업장 등록
    @PostMapping("/register")
    public ResponseEntity<Void> registerStore(@RequestBody StoreRequestDto storeRequestDto) {
        storeService.saveStore(storeRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //직원 목록 조회
    @GetMapping("/{storeId}/staffs")
    public List<Staff> getStaffsByStoreId(@PathVariable Long storeId) {
        return storeService.getStaffsByStoreId(storeId);
    }

    //직원 추가 => 초대 방식
    @PostMapping("/{code}/add/staff")
    public void addStaffToStore(@PathVariable Long code, @RequestBody StaffRequestDto staffRequestDto) {
        storeService.addStaffToStore(code, staffRequestDto);
    }
}
