package com.sstep.demo.notice.domain;

import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.staff.domain.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Notice {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //공지 고유번호
    private String title; //공지글 제목
    private LocalDateTime writeDate; //공지글 작성 일자
    private String contents; //공지글 내용

    private int hits; //공지 조회수

    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public int getHits() {
        return hits;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos.addAll(photos);
        photos.forEach(photo -> photo.setNotice(this));

    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<Photo> photos;

    @ManyToOne
    private Staff staff;
}
