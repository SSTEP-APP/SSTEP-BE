package com.sstep.demo.calendar.service;

import com.sstep.demo.calendar.CalendarMapper;
import com.sstep.demo.calendar.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final CalendarMapper calendarMapper;
}
