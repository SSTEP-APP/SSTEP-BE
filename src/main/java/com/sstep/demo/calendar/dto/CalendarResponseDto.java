package com.sstep.demo.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CalendarResponseDto {
    private long id; //일정 고유번호
    private String calendarDate; //일자
    private DayOfWeek dayOfWeek; //요일
    private String startCalTime; //근무 시작 시간
    private String endCalTime; //근무 종료 시간
    private String staffName; //직원 이름
}
