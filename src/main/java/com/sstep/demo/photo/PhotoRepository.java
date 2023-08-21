package com.sstep.demo.photo;

import com.sstep.demo.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query("SELECT p FROM Photo p WHERE p.notice.id = :id ")
    Set<Photo> findPhotosByNoticeId(long id);

    @Query("SELECT p FROM Photo p WHERE p.checkList.id = :id ")
    Set<Photo> findPhotosByCheckListId(long id);
}
