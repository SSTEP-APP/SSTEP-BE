package com.sstep.demo.staff;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Set;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> { //<Entity 클래스, PK 타입>


    @Query("SELECT s.notices FROM Staff s WHERE s.id = :staffId ")
    Set<Notice> findNoticesByStaffId(Long staffId);
}
