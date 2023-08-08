package com.sstep.demo.workdoc.domain;

import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.staff.domain.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class WorkDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //근로 계약서 고유번호
    private boolean isFirstRegister; //근로 계약서 등록 여부
    private boolean isSecondRegister; //근로 계약서 등록 여부

    public void setFirstRegister(boolean firstRegister) {
        isFirstRegister = firstRegister;
    }

    public void setSecondRegister(boolean secondRegister) {
        isSecondRegister = secondRegister;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @OneToOne(mappedBy = "workDoc")
    private Staff staff;

    @OneToOne
    private Photo photo;
}
