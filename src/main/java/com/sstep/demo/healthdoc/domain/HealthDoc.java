package com.sstep.demo.healthdoc.domain;

import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.staff.domain.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class HealthDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //보건증 고유번호
    private LocalDate checkUpDate; //보건증 검진일
    private LocalDate expirationDate; //보건증 만료일
    private boolean isRegister; //보건증 등록 여부

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @OneToOne(mappedBy = "healthDoc")
    private Staff staff;

    @OneToOne
    private Photo photo;
}
