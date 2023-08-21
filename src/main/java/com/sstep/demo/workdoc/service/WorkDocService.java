package com.sstep.demo.workdoc.service;

import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.workdoc.domain.WorkDoc;
import com.sstep.demo.workdoc.dto.WorkDocResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WorkDocService {

    private final StaffRepository staffRepository;
    private final StoreRepository storeRepository;
    private final PhotoService photoService;

    public void saveFirst(Long staffId, MultipartFile multipartFile) throws IOException {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        WorkDoc workDoc = new WorkDoc();
        if (!multipartFile.isEmpty()) {
            Photo photo = photoService.savePhoto(multipartFile);
            workDoc.setPhoto(photo);
            workDoc.setFirstRegister(true);
            staff.setWorkDoc(workDoc);
            staffRepository.save(staff);
        }
    }

    public void saveSecond(Long staffId, MultipartFile multipartFile) throws IOException {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        WorkDoc workDoc = new WorkDoc();
        if (!multipartFile.isEmpty() && staff.getWorkDoc().isFirstRegister()) {
            Photo photo = photoService.savePhoto(multipartFile);
            workDoc.setPhoto(photo);
            workDoc.setFirstRegister(false);
            workDoc.setSecondRegister(true);
            staff.setWorkDoc(workDoc);
            staffRepository.save(staff);
        }
    }

    public WorkDocResponseDto getInfoForWorkDoc(Long storeId, Long staffId) {
        Store store = getStore(storeId);
        Staff staff = getStaff(staffId); //근로 계약서 작성 직원
        Staff owner = storeRepository.findOwnerById(storeId); //사장

        WorkDocResponseDto workDocResponseDto = new WorkDocResponseDto();
        workDocResponseDto.setStoreName(store.getName());
        workDocResponseDto.setStoreAddress(store.getAddress());
        workDocResponseDto.setStorePhoneNum(owner.getMember().getPhoneNum());
        workDocResponseDto.setStoreOwnerName(owner.getMember().getName());
        workDocResponseDto.setStartDay(staff.getStartDay().toLocalDate());
        workDocResponseDto.setHourMoney(staff.getHourMoney());
        workDocResponseDto.setWageType(staff.getWageType());
        workDocResponseDto.setPaymentDate(staff.getPaymentDate());

        return workDocResponseDto;
    }

    public Set<Staff> getRegWorkDocStaffs(Long storeId) {
        Store store = getStore(storeId);
        Set<Staff> staffList = store.getStaffList();
        Set<Staff> staffs = new HashSet<>();
        for (Staff staff : staffList) {
            if (staff.getWorkDoc().isSecondRegister()) {
                staffs.add(staff);
            }
        }
        return staffs;
    }

    public Set<Staff> getUnRegWorkDocStaffs(Long storeId) {
        Store store = getStore(storeId);
        Set<Staff> staffList = store.getStaffList();
        Set<Staff> staffs = new HashSet<>();
        for (Staff staff : staffList) {
            if (!staff.getWorkDoc().isSecondRegister()) {
                staffs.add(staff);
            }
        }
        return staffs;
    }

    private Staff getStaff(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow();
    }

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow();
    }
}
