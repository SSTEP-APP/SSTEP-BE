package com.sstep.demo.store.service;

import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.store.StoreMapper;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.dto.StoreRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public Store getEntity(long code) {
        return storeRepository.findByCode(code).orElseThrow(EntityNotFoundException::new);
    }

    public void saveStore(StoreRequestDto storeRequestDto) {
        storeRepository.save(storeMapper.toEntity(storeRequestDto));
    }

    public List<Staff> getStaffsByStoreId(Long storeId) {
        return storeRepository.findStaffsByStoreId(storeId);
    }

    /*public void addStaffToStore(Long code, StaffRequestDto staffRequestDto) {
        Store store = getEntity(code);
        List<Staff> staffList = getStaffsByStoreId(store.getId());
        staffList.add(storeMapper.toStaffEntity(staffRequestDto));
    }*/
}
