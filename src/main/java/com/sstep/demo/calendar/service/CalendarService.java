package com.sstep.demo.calendar.service;

import com.sstep.demo.calendar.CalendarMapper;
import com.sstep.demo.calendar.CalendarRepository;
import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final StaffRepository staffRepository;
    private final CalendarMapper calendarMapper;
    private final StaffService staffService;


    public void saveCalendar(CalendarRequestDto calendarRequestDto, Long staffId) {
        Staff staff = getStaffById(staffId);

        Calendar calendar = getCalendarEntity(calendarRequestDto);
        calendarRepository.save(calendar);

        Set<Calendar> calendars = getCalendarsByStaffId(staffId);
        calendars.add(calendar);
        staff.setCalendars(calendars);
        staffRepository.save(staff);
    }

    public Set<Staff> getDayWorkStaffs(Long storeId, CalendarRequestDto calendarRequestDto) {
        return calendarRepository.findDayWorkStaffsByDate(storeId, calendarRequestDto.getCalendarDate(), calendarRequestDto.getDayOfWeek());
    }

    private Staff getStaffById(Long staffId) {
        return staffService.getStaffById(staffId);
    }

    private Calendar getCalendarEntity(CalendarRequestDto calendarRequestDto) {
        return calendarMapper.toCalendarEntity(calendarRequestDto);
    }

    private Set<Calendar> getCalendarsByStaffId(Long staffId) {
        return calendarRepository.findCalendarsByStaffId(staffId);
    }
}
