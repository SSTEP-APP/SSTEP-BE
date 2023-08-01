package com.sstep.demo.staff;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.schedule.dto.ScheduleRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Schedule ToScheduleEntity(ScheduleRequestDto scheduleRequestDto);

    Calendar toCalendarEntity(CalendarRequestDto calendarRequestDto);
}
