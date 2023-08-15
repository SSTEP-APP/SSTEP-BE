package com.sstep.demo.schedule;

import com.sstep.demo.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
