package com.sstep.demo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ScheduleRequestDto {
    private long id;
    private DayOfWeek weekDay;
    private LocalTime startTime;
    private LocalTime endTime;

}
