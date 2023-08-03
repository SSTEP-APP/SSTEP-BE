package com.sstep.demo.notice;

import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n.photos FROM Notice n WHERE n.id = :id ")
    List<Photo> findPhotosById(long id);

    Notice findNoticeById(Long noticeId);
}
