package com.sstep.demo.store.service;

import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.dto.StoreRegisterReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final StaffRepository staffRepository;

    public Store getCodeToEntity(long code) {
        return storeRepository.findByCode(code).orElseThrow(EntityNotFoundException::new);
    }

    private List<Staff> getStaffsByMemberId(Long memberId) {
        return storeRepository.findStaffsByMemberId(memberId);
    }

    public List<Staff> getStaffsByStoreId(Long storeId) {
        return storeRepository.findStaffsByStoreId(storeId);
    }

    public void saveStore(StoreRegisterReqDto storeRequestDto) {
        Store store = Store.builder()
                .name(storeRequestDto.getStoreName())
                .address(storeRequestDto.getStoreAddress())
                .latitude(storeRequestDto.getLatitude())
                .longitude(storeRequestDto.getLongitude())
                .scale(storeRequestDto.isScale())
                .plan(storeRequestDto.isPlan())
                .code(storeRequestDto.getCode())
                .staffList(new ArrayList<>())
                .build();

        storeRepository.save(store);
    }

    public void addOwnerToStore(StoreRegisterReqDto dto) {
        Store store = getCodeToEntity(dto.getCode());
        Member member = memberRepository.findByUsername(dto.getMemberUsername());

        Staff staff = Staff.builder()
                .joinStatus(true)
                .ownerStatus(true)
                .member(member)
                .build();

        List<Staff> memberStaff = getStaffsByMemberId(member.getId());
        memberStaff.add(staff);
        member.setStaffList(memberStaff);

        List<Staff> staffList = getStaffsByStoreId(store.getId());
        staffList.add(staff);
        store.setStaffList(staffList);

        staffRepository.save(staff);
        memberRepository.save(member);
        storeRepository.save(store);
    }


    public void inviteMemberToStore(StaffRequestDto dto) {
        Store store = getCodeToEntity(dto.getCode());
        Member member = memberRepository.findByUsername(dto.getUsername());
        Staff staff = Staff.builder()
                .joinStatus(true) //합류여부
                .member(member)
                .build();

        List<Staff> memberStaff = getStaffsByMemberId(member.getId());
        memberStaff.add(staff);
        member.setStaffList(memberStaff);

        List<Staff> staffList = getStaffsByStoreId(store.getId());
        staffList.add(staff);
        store.setStaffList(staffList);

        staffRepository.save(staff);
        memberRepository.save(member);
        storeRepository.save(store);
    }

    /*public void addMemberToStore(StaffRequestDto dto) {
        Store store = getCodeToEntity(dto.getCode());
        Member member = memberRepository.findByUsername(dto.getUsername());

        Staff staff = staffRepository.findById(dto.getId()).orElseThrow();
        staff.setStartDay(dto.getStartDay());
        staff.setPaymentDate(dto.getPaymentDate());
        staff.setHourMoney(dto.getHourMoney());
        staff.setWageType(dto.getWageType());
        staff.setSubmitStatus();

        List<Staff> memberStaff = getStaffsByMemberId(member.getId());
        memberStaff.add(staff);
        member.setStaffList(memberStaff);

        List<Staff> staffList = getStaffsByStoreId(store.getId());
        staffList.add(staff);
        store.setStaffList(staffList);

        staffRepository.save(staff);
        memberRepository.save(member);
        storeRepository.save(store);
    }*/

    public List<Staff> getUnRegStaffs(Long storeId) {
        return storeRepository.findUnRegStaffsByStoreId(storeId);
    }


    public List<Staff> getDayWorkStaffs(Long storeId, CalendarRequestDto calendarRequestDto) {
        return storeRepository.findDayWorkStaffsByDate(storeId, calendarRequestDto.getCalendarDate(), calendarRequestDto.getDayOfWeek());
    }

    public List<Staff> getDisputeStaffs(Long storeId) {
        return storeRepository.findDisputeStaffsByExistMessage(storeId);
    }

    public List<Notice> getNotices(Long storeId) {
        List<Staff> staffs = getStaffsByStoreId(storeId);
        List<Notice> notices = new ArrayList<>();
        for (Staff staff : staffs) {
            if (!staff.getNotices().isEmpty()) {
                notices.addAll(staff.getNotices());
            }
        }
        return notices;
    }
}
