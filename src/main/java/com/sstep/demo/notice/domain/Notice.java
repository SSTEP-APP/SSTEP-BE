package com.sstep.demo.notice.domain;

import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.staff.domain.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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
    private String writeDate; //공지글 작성 일자
    private String contents; //공지글 내용
    private int hits; //공지 조회수

    public long getId() {
        return id;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos.clear();
        if (photos != null) {
            this.photos.addAll(photos);
            photos.forEach(photo -> photo.setNotice(this));
        }
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private Set<Photo> photos;

    @ManyToOne
    private Staff staff;
}
