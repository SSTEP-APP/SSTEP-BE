package com.sstep.demo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ScheduleRequestDto {
    private long id;
    private DayOfWeek weekDay;
    private String startTime;
    private String endTime;

}
