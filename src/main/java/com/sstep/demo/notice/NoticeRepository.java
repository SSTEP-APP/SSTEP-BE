package com.sstep.demo.notice;

import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("SELECT n FROM Notice n WHERE n.staff.id = :staffId ")
    Set<Notice> findNoticesByStaffId(Long staffId);
}
