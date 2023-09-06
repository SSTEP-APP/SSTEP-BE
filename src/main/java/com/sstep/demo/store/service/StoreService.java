package com.sstep.demo.store.service;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.category.domain.Category;
import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.healthdoc.HealthDocRepository;
import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffInviteResponseDto;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.staff.dto.StaffResponseDto;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.dto.StoreRegisterReqDto;
import com.sstep.demo.store.dto.StoreResponseDto;
import com.sstep.demo.workdoc.WorkDocRepository;
import com.sstep.demo.workdoc.domain.WorkDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final StaffRepository staffRepository;
    private final HealthDocRepository healthDocRepository;
    private final WorkDocRepository workDocRepository;

    public void saveStore(StoreRegisterReqDto storeRequestDto) {
        Set<Staff> staffList = new HashSet<>();
        Set<Category> categories = new HashSet<>();

        Store store = Store.builder()
                .name(storeRequestDto.getStoreName())
                .address(storeRequestDto.getStoreAddress())
                .latitude(storeRequestDto.getLatitude())
                .longitude(storeRequestDto.getLongitude())
                .scale(storeRequestDto.isScale())
                .plan(storeRequestDto.isPlan())
                .code(storeRequestDto.getCode())
                .build();

        store.setCategories(categories);
        store.setStaffList(staffList);
        storeRepository.save(store);
    }

    public void addOwnerToStore(StoreRegisterReqDto dto) {
        Store store = getCodeToEntity(dto.getCode());
        Member member = getMemberByUsername(dto.getMemberUsername());
        Set<CheckList> checkLists = new HashSet<>();
        Set<Notice> notices = new HashSet<>();

        Staff staff = Staff.builder()
                .joinStatus(false)
                .ownerStatus(true)
                .submitStatus(false)
                .build();

        staff.setMember(member);
        staff.setStore(store);
        staff.setCheckLists(checkLists);
        staff.setNotices(notices);
        saveStaff(store, member, staff);
    }

    public StoreResponseDto getStoreByCode(Long code) {
        Store findStore = storeRepository.findByCode(code).orElseThrow();

        return StoreResponseDto.builder()
                .id(findStore.getId())
                .name(findStore.getName())
                .address(findStore.getAddress())
                .latitude(findStore.getLatitude())
                .longitude(findStore.getLongitude())
                .scale(findStore.isScale())
                .plan(findStore.isPlan())
                .code(findStore.getCode())
                .count(findStore.getStaffList().size())
                .build();
    }

    public void inviteMemberToStore(StaffRequestDto dto) {
        Store store = getCodeToEntity(dto.getCode());
        Member member = memberRepository.findByUsername(dto.getUsername());
        Set<Schedule> schedules = new HashSet<>();
        Set<CheckList> checkLists = new HashSet<>();
        Set<Notice> notices = new HashSet<>();
        Set<Calendar> calendars = new HashSet<>();
        Set<Commute> commutes = new HashSet<>();

        Staff staff = Staff.builder()
                .joinStatus(true) //합류여부
                .commutes(new HashSet<>())
                .calendars(new HashSet<>())
                .build();

        staff.setMember(member);
        staff.setStore(store);
        staff.setSchedules(schedules);
        staff.setCheckLists(checkLists);
        staff.setNotices(notices);
        staff.setCommutes(commutes);
        staff.setCalendars(calendars);
        saveStaff(store, member, staff);
    }

    public void inputCode(long staffId) {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        staff.setJoinStatus(false);
        staff.setSubmitStatus(true); //코드 입력 여부

        staffRepository.save(staff);
    }

    public void addMemberToStore(StaffRequestDto dto) {
        Staff staff = staffRepository.findById(dto.getId()).orElseThrow();

        HealthDoc healthDoc = HealthDoc.builder()
                .staff(staff)
                .build();
        healthDocRepository.save(healthDoc);

        WorkDoc workDoc = WorkDoc.builder()
                .staff(staff)
                .build();
        workDocRepository.save(workDoc);

        staff.setStartDay(dto.getStartDay());
        staff.setPaymentDate(dto.getPaymentDate());
        staff.setHourMoney(dto.getHourMoney());
        staff.setWageType(dto.getWageType());
        staff.setSubmitStatus(false);
        staff.setWorkDoc(workDoc);
        staff.setHealthDoc(healthDoc);

        staffRepository.save(staff);
    }

    public Set<StaffInviteResponseDto> getInviteStaffs(Long storeId) {
        Set<StaffInviteResponseDto> staffs = new HashSet<>();
        for (Staff findStaff : storeRepository.findInviteStaffsByStoreId(storeId)) {
            StaffInviteResponseDto staff = StaffInviteResponseDto.builder()
                    .username(findStaff.getMember().getUsername())
                    .name(findStaff.getMember().getName())
                    .staffId(findStaff.getId())
                    .build();

            staffs.add(staff);
        }
        return staffs;
    }

    public Set<StaffInviteResponseDto> getInputCodeStaffs(Long storeId) {
        Set<StaffInviteResponseDto> staffs = new HashSet<>();
        for (Staff findStaff : storeRepository.findInputCodeStaffsByStoreId(storeId)) {
            StaffInviteResponseDto staff = StaffInviteResponseDto.builder()
                    .username(findStaff.getMember().getUsername())
                    .name(findStaff.getMember().getName())
                    .staffId(findStaff.getId())
                    .build();

            staffs.add(staff);
        }

        return staffs;
    }

    public Set<StaffResponseDto> getStaffsByStoreId(Long storeId) {
        Set<StaffResponseDto> staff = new HashSet<>();
        for (Staff findStaff : storeRepository.findStaffsByStoreId(storeId)) {
            StaffResponseDto s = StaffResponseDto.builder()
                    .hourMoney(findStaff.getHourMoney())
                    .id(findStaff.getId())
                    .staffName(findStaff.getMember().getName())
                    .startDay(findStaff.getStartDay())
                    .wageType(findStaff.getWageType())
                    .submitStatus(findStaff.isSubmitStatus())
                    .paymentDate(findStaff.getPaymentDate())
                    .ownerStatus(findStaff.isOwnerStatus())
                    .joinStatus(findStaff.isJoinStatus())
                    .build();

            staff.add(s);
        }

        return staff;
    }

    public Store getCodeToEntity(long code) {
        return storeRepository.findByCode(code).orElseThrow(EntityNotFoundException::new);
    }

    private Set<Staff> getStaffsByMemberId(Long memberId) {
        return storeRepository.findStaffsByMemberId(memberId);
    }


    private void saveStaff(Store store, Member member, Staff staff) {
        staffRepository.save(staff);

        Set<Staff> memberStaff = getStaffsByMemberId(member.getId());
        memberStaff.add(staff);
        member.setStaffList(memberStaff);
        memberRepository.save(member);

        Set<Staff> staffList = storeRepository.findStaffsByStoreId(store.getId());
        staffList.add(staff);
        store.setStaffList(staffList);
        storeRepository.save(store);
    }

    private Member getMemberByUsername(String memberUsername) {
        return memberRepository.findByUsername(memberUsername);
    }
}
