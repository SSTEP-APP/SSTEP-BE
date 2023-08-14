package com.sstep.demo.staff.service;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.commute.dto.CommuteRequestDto;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.notice.service.NoticeService;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.schedule.dto.ScheduleRequestDto;
import com.sstep.demo.staff.StaffMapper;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
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


    public Staff getStaff(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow();
    }

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

    public void saveSchedule(ScheduleRequestDto scheduleRequestDto, Long staffId) {
        Staff staff = getStaff(staffId);
        Set<Schedule> schedules = getSchedulesByStaffId(staffId);
        Schedule schedule = getScheduleEntity(scheduleRequestDto);
        schedules.add(schedule);
        staff.setSchedules(schedules);
        staffRepository.save(staff);
    }

    private Schedule getScheduleEntity(ScheduleRequestDto scheduleRequestDto) {
        return staffMapper.ToScheduleEntity(scheduleRequestDto);
    }


    private Set<Schedule> getSchedulesByStaffId(long id) {
        return staffRepository.findSchedulesByStaffId(id);
    }

    public void saveCalendar(CalendarRequestDto calendarRequestDto, Long staffId) {
        Staff staff = getStaff(staffId);
        Set<Calendar> calendars = getCalendarsByStaffId(staffId);
        Calendar calendar = getCalendarEntity(calendarRequestDto);
        calendars.add(calendar);
        staff.setCalendars(calendars);
        staffRepository.save(staff);
    }

    private Calendar getCalendarEntity(CalendarRequestDto calendarRequestDto) {
        return staffMapper.toCalendarEntity(calendarRequestDto);
    }

    private Set<Calendar> getCalendarsByStaffId(Long staffId) {
        return staffRepository.findCalendarsByStaffId(staffId);
    }

    public void saveCommute(CommuteRequestDto commuteRequestDto, Long staffId) {
        Staff staff = getStaff(staffId);
        boolean late = isLate(commuteRequestDto, staff.getSchedules());

        Set<Commute> commutes = getCommutesByStaffId(staffId);
        Commute commute = getCommuteEntity(commuteRequestDto);
        commute.setLate(late);
        commutes.add(commute);
        staff.setCommutes(commutes);
        staffRepository.save(staff);
    }

    private boolean isLate(CommuteRequestDto commuteRequestDto, Set<Schedule> schedules) { //지각 여부 확인
        //해당 직원의 해당 날짜의 출근 시간 가져와서 비교
        //기준 시간 10분 이후부터 지각 처리
        for (Schedule schedule : schedules) {
            if (schedule.getWeekDay() == commuteRequestDto.getDayOfWeek()) {
                if (commuteRequestDto.getStartTime().isAfter(schedule.getStartTime().plusMinutes(10))) {
                    return true;
                }
            }
        }
        return false;
    }

    private Commute getCommuteEntity(CommuteRequestDto commuteRequestDto) {
        return staffMapper.toCommuteEntity(commuteRequestDto);
    }

    private Set<Commute> getCommutesByStaffId(Long staffId) {
        return staffRepository.findCommutesByStaffId(staffId);
    }

    public void updateCommute(Long staffId, Long commuteId, CommuteRequestDto commuteRequestDto) {
        Commute existingCommute = staffRepository.findByCommuteIdAndStoreId(staffId, commuteId);
        if (existingCommute != null) {
            //출퇴근 정보가 이미 존재하면 퇴근시간 정보만 업데이트 진행
            existingCommute.setEndTime(commuteRequestDto.getEndTime());
        } else {
            throw new RuntimeException("해당 직원을 찾을 수 없습니다.");
        }
    }

    public void disputeCommute(Long staffId, Long commuteId, CommuteRequestDto commuteRequestDto) {
        Commute existingCommute = staffRepository.findByCommuteIdAndStoreId(staffId, commuteId);
        if (existingCommute != null) {
            //출퇴근 정보가 이미 존재하면 이의 신청 메시지 업데이트
            existingCommute.setDisputeMessage(commuteRequestDto.getDisputeMessage());
        } else {
            throw new RuntimeException("해당 직원을 찾을 수 없습니다.");
        }
    }

    public void UpdateDisputeCommute(Long staffId, Long commuteId, CommuteRequestDto commuteRequestDto) {
        Commute existingCommute = staffRepository.findByCommuteIdAndStoreId(staffId, commuteId);
        if (existingCommute != null) {
            //출퇴근 정보가 이미 존재하면 이의 신청 메시지 null로 업데이트
            existingCommute.setDisputeMessage(null);
            existingCommute.setStartTime(commuteRequestDto.getStartTime());
            existingCommute.setEndTime(commuteRequestDto.getEndTime());
        } else {
            throw new RuntimeException("해당 직원을 찾을 수 없습니다.");
        }
    }

    public Set<Commute> getDisputeList(Long storeId, Long staffId) {
        return staffRepository.findDisputeListByStoreIdAndStaffId(storeId, staffId);
    }

    public void saveNotice(Long staffId, NoticeRequestDto noticeRequestDto, MultipartFile[] multipartFile) throws IOException {
        Staff staff = getStaff(staffId);

        Set<Notice> notices = getNoticesByStaffId(staffId);
        Notice notice = getNoticeEntity(noticeRequestDto);
        if (Arrays.stream(multipartFile).findAny().isPresent()) {
            for (MultipartFile imageFile : multipartFile) {
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
