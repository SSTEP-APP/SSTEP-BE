package com.sstep.demo.schedule.service;

import com.sstep.demo.schedule.ScheduleRepository;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.schedule.dto.ScheduleRequestDto;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final StaffRepository staffRepository;
    private final StaffService staffService;

    public void saveSchedule(ScheduleRequestDto scheduleRequestDto, Long staffId) {
        Staff staff = staffService.getStaffById(staffId);

        Schedule schedule = Schedule.builder()
                .endTime(scheduleRequestDto.getEndTime())
                .id(scheduleRequestDto.getId())
                .staff(staff)
                .startTime(scheduleRequestDto.getStartTime())
                .weekDay(scheduleRequestDto.getWeekDay())
                .build();

        scheduleRepository.save(schedule);

        Set<Schedule> schedules = getSchedulesByStaffId(staffId);
        schedules.add(schedule);
        staff.setSchedules(schedules);
        staffRepository.save(staff);
    }

    private Set<Schedule> getSchedulesByStaffId(Long staffId) {
        return scheduleRepository.findSchedulesByStaffId(staffId);
    }
}
