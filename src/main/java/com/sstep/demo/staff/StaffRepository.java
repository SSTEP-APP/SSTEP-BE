package com.sstep.demo.staff;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> { //<Entity 클래스, PK 타입>
    Staff findByIdAndStoreId(Long staffId, Long storeId);

    @Query("SELECT s.schedules FROM Staff s WHERE s.id = :staffId")
    Set<Schedule> findSchedulesByStaffId(@Param("staffId") Long staffId);

    @Query("SELECT s.calendars FROM Staff s WHERE s.id = :staffId")
    Set<Calendar> findCalendarsByStaffId(Long staffId);

    @Query("SELECT s.commutes FROM Staff s WHERE s.id = :staffId")
    Set<Commute> findCommutesByStaffId(Long staffId);

    @Query("SELECT s.commutes FROM Staff s, Commute c WHERE s.id = :staffId and c.id = :commuteId")
    Commute findByCommuteIdAndStoreId(Long staffId, Long commuteId);

    @Query("SELECT s.commutes FROM Staff s,Store st WHERE st.id = :storeId and s.id = :staffId ")
    Set<Commute> findDisputeListByStoreIdAndStaffId(Long storeId, Long staffId);

    @Query("SELECT s.notices FROM Staff s WHERE s.id = :staffId ")
    Set<Notice> findNoticesByStaffId(Long staffId);

    @Query("SELECT s.staffList FROM  Store s, Member m WHERE s.code = :code and m.username = :username ")
    Staff findByStoreCodeAndUsername(long code, String username);
}
