package com.sstep.demo.schedule.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ScheduleRequestDto {
    private long id;
    private DayOfWeek weekDay;
    private LocalTime startTime;
    private LocalTime endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ScheduleRequestDto(long id, DayOfWeek weekDay, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
