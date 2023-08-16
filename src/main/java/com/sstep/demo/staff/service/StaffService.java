package com.sstep.demo.staff.service;

import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.commute.dto.CommuteRequestDto;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.notice.service.NoticeService;
import com.sstep.demo.staff.StaffMapper;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.staff.dto.StaffResponseDto;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final NoticeService noticeService;
    private final StoreRepository storeRepository;

    public StaffResponseDto getStaff(Long staffId) {
        Staff findStaff = getStaffById(staffId);

        return StaffResponseDto.builder()
                .hourMoney(findStaff.getHourMoney())
                .joinStatus(findStaff.isJoinStatus())
                .ownerStatus(findStaff.isOwnerStatus())
                .paymentDate(findStaff.getPaymentDate())
                .startDay(findStaff.getStartDay())
                .submitStatus(findStaff.isSubmitStatus())
                .wageType(findStaff.getWageType())
                .build();
    }

    public Staff getStaffById(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow();
    }

    public void updateStaff(Long staffId, StaffRequestDto staffRequestDto) {
        Staff existingStaff = staffRepository.findById(staffId).orElseThrow();
        existingStaff.setHourMoney(staffRequestDto.getHourMoney());
        existingStaff.setPaymentDate(staffRequestDto.getPaymentDate());
        existingStaff.setStartDay(staffRequestDto.getStartDay());
        existingStaff.setWageType(staffRequestDto.getWageType());

        staffRepository.save(existingStaff);
    }


    public void saveNotice(Long staffId, NoticeRequestDto noticeRequestDto) throws IOException {
        Staff staff = getStaffById(staffId);

        Set<Notice> notices = getNoticesByStaffId(staffId);
        Notice notice = getNoticeEntity(noticeRequestDto);
        if (Arrays.stream(noticeRequestDto.getMultipartFile()).findAny().isPresent()) {
            for (MultipartFile imageFile : noticeRequestDto.getMultipartFile()) {
                noticeService.saveNotice(notice, imageFile);
            }
        }
        notices.add(notice);
        staff.setNotices(notices);
        staffRepository.save(staff);
    }

    private Notice getNoticeEntity(NoticeRequestDto noticeRequestDto) {
        return staffMapper.toNoticeEntity(noticeRequestDto);
    }

    private Set<Notice> getNoticesByStaffId(Long staffId) {
        return staffRepository.findNoticesByStaffId(staffId);
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

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow();
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
}
