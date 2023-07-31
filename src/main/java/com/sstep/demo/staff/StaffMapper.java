package com.sstep.demo.staff;

import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.schedule.dto.ScheduleRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Schedule ToScheduleEntity(ScheduleRequestDto scheduleRequestDto);
}