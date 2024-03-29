package com.sstep.demo.calendar;

import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.calendar.dto.CalendarResponseDto;
import com.sstep.demo.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    //직원별 캘린더(일정) 저장
    @PostMapping("/{staffId}/add-calendar")
    public ResponseEntity<Void> registerCalendar(@PathVariable Long staffId, @RequestBody CalendarRequestDto calendarRequestDto) {
        calendarService.saveCalendar(calendarRequestDto, staffId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //해당 날짜에 근무하는 직원 리스트 가져오기
    @GetMapping("/{storeId}/{date}/{day}/day-work-staffs")
    public Set<CalendarResponseDto> getDayWorkStaffs(@PathVariable Long storeId, @PathVariable String date, @PathVariable DayOfWeek day) {
        return calendarService.getDayWorkStaffs(storeId, date, day);
    }
}
