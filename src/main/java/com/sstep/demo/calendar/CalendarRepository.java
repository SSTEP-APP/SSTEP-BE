package com.sstep.demo.calendar;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.Set;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Query("SELECT s.calendars FROM Staff s WHERE s.id = :staffId")
    Set<Calendar> findCalendarsByStaffId(Long staffId);

    //캘린더에 별도 추가로 추가 근무하는 직원 리스트 + 스케줄에 등록해 고정으로 근무하는 직원 리스트
    @Query("SELECT c.staff, sc.staff FROM Store s,Calendar c,Schedule sc WHERE (c.staff.store.id = :storeId and " +
            "c.calendarDate = :date) or (sc.weekDay = :day and sc.staff.store.id = :storeId)")
    Set<Staff> findDayWorkStaffsByDate(Long storeId, Date date, DayOfWeek day);
}
