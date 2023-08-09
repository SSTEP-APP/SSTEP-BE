package com.sstep.demo.store.service;

import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.member.MemberMapper;
import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.store.StoreMapper;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.dto.StoreRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Store getEntity(long code) {
        return storeRepository.findByCode(code).orElseThrow(EntityNotFoundException::new);
    }

    public void saveStore(StoreRequestDto storeRequestDto) {
        storeRepository.save(storeMapper.toEntity(storeRequestDto));
    }

    public void addStaffToStore(Long code, StaffRequestDto staffRequestDto, MemberRequestDto memberRequestDto) {
        Store store = getEntity(code);
        Staff staff = getStaffEntity(staffRequestDto);
        Member member = getMemberEntity(memberRequestDto);
        List<Staff> staffList = getStaffsByStoreId(store.getId());
        List<Staff> memberStaff = getStaffsByMemberId(memberRequestDto.getMemberId());
        memberStaff.add(staff);
        staffList.add(staff);
        member.setStaffList(memberStaff);
        store.setStaffList(staffList);
        storeRepository.save(store);
        memberRepository.save(member);
    }

    private Member getMemberEntity(MemberRequestDto memberRequestDto) {
        return memberMapper.toEntity(memberRequestDto);
    }

    private Staff getStaffEntity(StaffRequestDto staffRequestDto) {
        return storeMapper.toStaffEntity(staffRequestDto);
    }

    private List<Staff> getStaffsByMemberId(String memberId) {
        return storeRepository.findStaffsByMemberId(memberId);
    }

    public List<Staff> getStaffsByStoreId(Long storeId) {
        return storeRepository.findStaffsByStoreId(storeId);
    }

    public void setOwner(StoreRequestDto storeRequestDto, StaffRequestDto staffRequestDto, MemberRequestDto memberRequestDto) {
        long code = storeRequestDto.getCode();
        staffRequestDto.setJoinStatus(true); //합류여부 true
        staffRequestDto.setOwnerStatus(true); //사장 여부 true
        addStaffToStore(code, staffRequestDto, memberRequestDto);
    }

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
