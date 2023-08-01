package com.sstep.demo.staff;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> { //<Entity 클래스, PK 타입>
    // 직원 고유번호를 기반으로 직원 정보를 조회하는 메서드
    Staff findByIdAndStoreId(Long staffId, Long storeId);

    @Query("SELECT s.schedules FROM Staff s WHERE s.id = :staffId")
    List<Schedule> findSchedulesByStaffId(@Param("staffId") Long staffId);

    @Query("SELECT s.calendars FROM Staff s WHERE s.id = :staffId")
    List<Calendar> findCalendarsByStaffId(Long staffId);
}
