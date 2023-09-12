package com.sstep.demo.checklistmanager.domain;

import com.sstep.demo.checklist.domain.CheckList;
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
public class CheckListManager {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //체크 리스트 담당자 고유번호
    private String name; //체크 리스트 담당자 이름
    private String phoneNum; //체크 리스트 담당자 연락처

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @ManyToOne
    private CheckList checkList;

    @ManyToOne
    private Staff staff;
}
