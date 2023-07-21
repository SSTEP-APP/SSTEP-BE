package com.sstep.demo.staff.service;

import com.sstep.demo.staff.StaffMapper;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.store.StoreMapper;
import com.sstep.demo.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public void updateStaff(Long storeId, Long staffId, StaffRequestDto staffRequestDto) {
        Staff existingStaff = staffRepository.findByIdAndStoreId(staffId, storeId);
        if (existingStaff != null) {
            existingStaff.setHourMoney(staffRequestDto.getHourMoney());
            existingStaff.setPaymentDate(staffRequestDto.getPaymentDate());
            existingStaff.setStartDay(staffRequestDto.getStartDay());
            existingStaff.setWageType(staffRequestDto.getWageType());
            existingStaff.setJoinStatus(true);
        } else {
            throw new RuntimeException("해당 직원을 찾을 수 없습니다.");
        }
    }

}
