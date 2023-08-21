package com.sstep.demo.healthdoc.service;

import com.sstep.demo.healthdoc.HealthDocRepository;
import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class HealthDocService {

    private final StaffRepository staffRepository;
    private final HealthDocRepository healthDocRepository;
    private final PhotoService photoService;
    private final StoreService storeService;

    /*public void saveHealthDoc(Long staffId, HealthDocRequestDto healthDocRequestDto, MultipartFile multipartFile) throws IOException {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        HealthDoc healthDoc = HealthDoc.builder()
                .checkUpDate(healthDocRequestDto.getCheckUpDate())
                .expirationDate(healthDocRequestDto.getExpirationDate())
                .isRegister(true)
                .build();
        if (!multipartFile.isEmpty()) {
            Photo photo = photoService.savePhoto(multipartFile);
            healthDoc.setPhoto(photo);
        }
        staff.setHealthDoc(healthDoc);
        staffRepository.save(staff);
    }*/

    public HealthDoc getHealthDoc(Long staffId) {
        return healthDocRepository.findByStaffId(staffId);
    }

    public Set<Staff> getRegHealthDocStaffs(Long storeId) {
        Store store = getStore(storeId);
        Set<Staff> staffList = store.getStaffList();
        Set<Staff> staffs = new HashSet<>();
        for (Staff staff : staffList) {
            if (staff.getHealthDoc().isRegister()) {
                staffs.add(staff);
            }
        }
        return staffs;
    }

    public Set<Staff> getUnRegHealthDocStaffs(Long storeId) {
        Store store = getStore(storeId);
        Set<Staff> staffList = store.getStaffList();
        Set<Staff> staffs = new HashSet<>();
        for (Staff staff : staffList) {
            if (!staff.getHealthDoc().isRegister()) {
                staffs.add(staff);
            }
        }
        return staffs;
    }

    private Store getStore(Long storeId) {
        return storeService.getStore(storeId);
    }
}
