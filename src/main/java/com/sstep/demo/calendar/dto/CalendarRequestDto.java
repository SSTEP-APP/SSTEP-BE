package com.sstep.demo.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CalendarRequestDto {
    private long id; //일정 고유번호
    private LocalDate calendarDate; //일자
    private DayOfWeek dayOfWeek; //요일
    private LocalTime startCalTime; //근무 시작 시간
    private LocalTime endCalTime; //근무 종료 시간
}
