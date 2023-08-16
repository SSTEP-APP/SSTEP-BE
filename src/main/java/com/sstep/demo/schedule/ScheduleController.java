package com.sstep.demo.schedule;


import com.sstep.demo.schedule.dto.ScheduleRequestDto;
import com.sstep.demo.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    //직원별 스케줄 저장
    @PostMapping("/{staffId}/add-schedule")
    public ResponseEntity<Void> registerSchedule(@PathVariable Long staffId, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        scheduleService.saveSchedule(scheduleRequestDto, staffId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
