package com.sstep.demo.workdoc.service;

import com.sstep.demo.photo.PhotoRepository;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.workdoc.WorkDocRepository;
import com.sstep.demo.workdoc.domain.WorkDoc;
import com.sstep.demo.workdoc.dto.WorkDocRequestDto;
import com.sstep.demo.workdoc.dto.WorkDocResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WorkDocService {

    private final StaffRepository staffRepository;
    private final StoreRepository storeRepository;
    private final WorkDocRepository workDocRepository;
    private final PhotoRepository photoRepository;

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

    public void saveFirst(Long staffId, WorkDocRequestDto workDocRequestDto) {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        WorkDoc workDoc = new WorkDoc();
        Photo photo = photoRepository.findById(workDocRequestDto.getPhotoId()).orElseThrow();

        workDoc.setPhoto(photo);
        workDoc.setStaff(staff);
        workDoc.setFirstRegister(true);
        workDocRepository.save(workDoc);

        photo.setWorkDoc(workDoc);
        photoRepository.save(photo);

        staff.setWorkDoc(workDoc);
        staffRepository.save(staff);
    }

    public void saveSecond(Long staffId, WorkDocRequestDto workDocRequestDto) {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        WorkDoc workDoc = new WorkDoc();
        Photo photo = photoRepository.findById(workDocRequestDto.getPhotoId()).orElseThrow();

        workDoc.setPhoto(photo);
        workDoc.setStaff(staff);
        workDoc.setFirstRegister(false);
        workDoc.setSecondRegister(true);
        workDocRepository.save(workDoc);

        photo.setWorkDoc(workDoc);
        photoRepository.save(photo);

        staff.setWorkDoc(workDoc);
        staffRepository.save(staff);
    }

    public PhotoResponseDto getFirstWorkDoc(Long staffId) {
        Staff staff = getStaff(staffId);
        Photo findPhoto = staff.getWorkDoc().getPhoto();

        return PhotoResponseDto.builder()
                .id(findPhoto.getId())
                .fileName(findPhoto.getFileName())
                .contentType(findPhoto.getContentType())
                .data(findPhoto.getData())
                .build();
    }

    public PhotoResponseDto getSecondWorkDoc(Long staffId) {
        Staff staff = getStaff(staffId);
        Photo findPhoto = staff.getWorkDoc().getPhoto();

        return PhotoResponseDto.builder()
                .id(findPhoto.getId())
                .fileName(findPhoto.getFileName())
                .contentType(findPhoto.getContentType())
                .data(findPhoto.getData())
                .build();
    }

    public Set<WorkDocResponseDto> getRegFirstWorkDocStaffs(Long storeId) {
        Store store = getStore(storeId);
        Set<Staff> staffList = store.getStaffList();
        Set<WorkDocResponseDto> staffs = new HashSet<>();
        for (Staff staff : staffList) {
            if (staff.getWorkDoc().isFirstRegister()) {
                WorkDocResponseDto st = WorkDocResponseDto.builder()
                        .staffId(staff.getId())
                        .staffName(staff.getMember().getName())
                        .build();

                staffs.add(st);
            }
        }
        return staffs;
    }

    public Set<WorkDocResponseDto> getRegSecondWorkDocStaffs(Long storeId) {
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
            if (!staff.getWorkDoc().isFirstRegister() && !staff.getWorkDoc().isSecondRegister()) {
                WorkDocResponseDto st = WorkDocResponseDto.builder()
                        .staffId(staff.getId())
                        .staffName(staff.getMember().getName())
                        .build();

                staffs.add(st);
            }
        }
        return staffs;
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
