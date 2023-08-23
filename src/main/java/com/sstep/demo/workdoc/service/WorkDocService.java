package com.sstep.demo.workdoc.service;

import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.workdoc.WorkDocRepository;
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
    private final WorkDocRepository workDocRepository;
    private final PhotoService photoService;

    public void saveFirst(Long staffId, MultipartFile multipartFile) throws IOException {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        WorkDoc workDoc = new WorkDoc();
        if (!multipartFile.isEmpty()) {
            Photo photo = photoService.savePhoto(multipartFile);
            workDoc.setPhoto(photo);
            workDoc.setFirstRegister(true);
            workDocRepository.save(workDoc);

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
            workDocRepository.save(workDoc);

            staff.setWorkDoc(workDoc);
            staffRepository.save(staff);
        }
    }

    public WorkDocResponseDto getInfoForWorkDoc(Long storeId, Long staffId) {
        Store store = getStore(storeId);
        Staff staff = getStaff(staffId); //근로 계약서 작성 직원
        Staff owner = getOwner(storeId); //사장

        return WorkDocResponseDto.builder()
                .staffId(staff.getId())
                .hourMoney(staff.getHourMoney())
                .paymentDate(staff.getPaymentDate())
                .staffName(staff.getMember().getName())
                .startDay(staff.getStartDay())
                .storeAddress(store.getAddress())
                .storeName(store.getName())
                .storeOwnerName(owner.getMember().getName())
                .storePhoneNum(owner.getMember().getPhoneNum())
                .wageType(staff.getWageType())
                .build();
    }

    public Set<WorkDocResponseDto> getRegWorkDocStaffs(Long storeId) {
        Store store = getStore(storeId);
        Set<Staff> staffList = store.getStaffList();
        Set<WorkDocResponseDto> staffs = new HashSet<>();
        for (Staff staff : staffList) {
            if (staff.getWorkDoc().isSecondRegister()) {
                WorkDocResponseDto st = WorkDocResponseDto.builder()
                        .staffId(staff.getId())
                        .staffName(staff.getMember().getName())
                        .build();

                staffs.add(st);
            }
        }
        return staffs;
    }

    public Set<WorkDocResponseDto> getUnRegWorkDocStaffs(Long storeId) {
        Store store = getStore(storeId);
        Set<Staff> staffList = store.getStaffList();
        Set<WorkDocResponseDto> staffs = new HashSet<>();
        for (Staff staff : staffList) {
            if (!staff.getWorkDoc().isSecondRegister()) {
                WorkDocResponseDto st = WorkDocResponseDto.builder()
                        .staffId(staff.getId())
                        .staffName(staff.getMember().getName())
                        .build();

                staffs.add(st);
            }
        }
        return staffs;
    }

    public PhotoResponseDto getFirstWorkDoc(Long staffId) {
        Staff staff = getStaff(staffId);
        Photo findPhoto = staff.getWorkDoc().getPhoto();

        return PhotoResponseDto.builder()
                .id(findPhoto.getId())
                .data(findPhoto.getData())
                .fileName(findPhoto.getFileName())
                .contentType(findPhoto.getContentType())
                .build();
    }

    private Staff getOwner(Long storeId) {
        return storeRepository.findOwnerById(storeId);
    }

    private Staff getStaff(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow();
    }

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow();
    }
}
