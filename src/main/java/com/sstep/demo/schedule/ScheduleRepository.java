package com.sstep.demo.schedule;

import com.sstep.demo.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s.schedules FROM Staff s WHERE s.id = :staffId")
    Set<Schedule> findSchedulesByStaffId(Long staffId);
}
