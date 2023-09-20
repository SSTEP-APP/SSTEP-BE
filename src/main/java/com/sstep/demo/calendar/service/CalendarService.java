package com.sstep.demo.calendar.service;

import com.sstep.demo.calendar.CalendarRepository;
import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.calendar.dto.CalendarResponseDto;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final StaffRepository staffRepository;
    private final StaffService staffService;


    public void saveCalendar(CalendarRequestDto calendarRequestDto, Long staffId) {
        Staff staff = getStaffById(staffId);

        Calendar calendar = Calendar.builder()
                .calendarDate(calendarRequestDto.getCalendarDate())
                .dayOfWeek(calendarRequestDto.getDayOfWeek())
                .startCalTime(calendarRequestDto.getStartCalTime())
                .endCalTime(calendarRequestDto.getEndCalTime())
                .build();

        calendar.setStaff(staff);
        calendarRepository.save(calendar);

        Set<Calendar> calendars = getCalendarsByStaffId(staffId);
        calendars.add(calendar);
        staff.setCalendars(calendars);
        staffRepository.save(staff);
    }

    public Set<CalendarResponseDto> getDayWorkStaffs(Long storeId, String date, DayOfWeek day) {
        Set<CalendarResponseDto> staffs = new HashSet<>();

        //캘린더에 등록된 직원 불러오는 기능
        for (Staff findStaff : calendarRepository.findCalStaffByDate(storeId, date)) {
            String calendarStartTime = getCalendarStartTime(findStaff, date);
            String calendarEndTime = getCalendarEndTime(findStaff, date);

            CalendarResponseDto dto = CalendarResponseDto.builder()
                    .staffName(findStaff.getMember().getName())
                    .startCalTime(calendarStartTime)
                    .endCalTime(calendarEndTime)
                    .dayOfWeek(day)
                    .calendarDate(date)
                    .build();

            staffs.add(dto);
        }

        //스케줄에 등록된 고정 직원 불러오는 기능
        for (Staff findStaff : calendarRepository.findScheduleStaffByDay(storeId, day)) {
            String scheduleStartTime = getScheduleStartTime(findStaff, day);
            String scheduleEndTime = getScheduleEndTime(findStaff, day);

            CalendarResponseDto dto = CalendarResponseDto.builder()
                    .staffName(findStaff.getMember().getName())
                    .startCalTime(scheduleStartTime)
                    .endCalTime(scheduleEndTime)
                    .dayOfWeek(day)
                    .calendarDate(date)
                    .build();

            staffs.add(dto);
        }
        return staffs;
    }

    private String getScheduleStartTime(Staff findStaff, DayOfWeek dayOfWeek) {
        for (Schedule schedule : findStaff.getSchedules()) {
            if (schedule.getWeekDay() == dayOfWeek) {
                return schedule.getStartTime();
            }
        }
        return null;
    }

    private String getCalendarStartTime(Staff findStaff, String date) {
        for (Calendar calendar : findStaff.getCalendars()) {
            if (calendar.getCalendarDate().equals(date)) {
                return calendar.getStartCalTime();
            }
        }
        return null;
    }

    private String getScheduleEndTime(Staff findStaff, DayOfWeek dayOfWeek) {
        for (Schedule schedule : findStaff.getSchedules()) {
            if (schedule.getWeekDay() == dayOfWeek) {
                return schedule.getEndTime();
            }
        }
        return null;
    }

    private String getCalendarEndTime(Staff findStaff, String date) {
        for (Calendar calendar : findStaff.getCalendars()) {
            if (calendar.getCalendarDate().equals(date)) {
                return calendar.getEndCalTime();
            }
        }
        return null;
    }

    private Staff getStaffById(Long staffId) {
        return staffService.getStaffById(staffId);
    }

    private Set<Calendar> getCalendarsByStaffId(Long staffId) {
        return calendarRepository.findCalendarsByStaffId(staffId);
    }
}
