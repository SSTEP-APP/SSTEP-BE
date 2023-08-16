package com.sstep.demo.calendar;

import com.sstep.demo.calendar.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Query("SELECT s.calendars FROM Staff s WHERE s.id = :staffId")
    Set<Calendar> findCalendarsByStaffId(Long staffId);
}
