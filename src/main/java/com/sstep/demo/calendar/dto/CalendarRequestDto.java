package com.sstep.demo.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CalendarRequestDto {
    private long id; //일정 고유번호
    private Date calendarDate; //일자
    private DayOfWeek dayOfWeek; //요일
    private LocalTime startCalTime;
    private LocalTime endCalTime;

    public Date getCalendarDate() {
        return calendarDate;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
