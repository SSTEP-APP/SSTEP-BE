package com.sstep.demo.calendar;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.calendar.dto.CalendarRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalendarMapper {

    Calendar toCalendarEntity(CalendarRequestDto calendarRequestDto);
}
