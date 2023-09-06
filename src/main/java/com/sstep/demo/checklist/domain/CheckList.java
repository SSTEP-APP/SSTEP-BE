package com.sstep.demo.checklist.domain;

import com.sstep.demo.category.domain.Category;
import com.sstep.demo.checklistmanager.domain.CheckListManager;
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
public class CheckList {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //체크 리스트 고유번호
    private String title; //체크 리스트 제목
    private String contents; //체크 리스트 내용
    private String endDay; //체크 리스트 마감 일자
    private String date; //체크 리스트 작성 일자
    private boolean needPhoto; //체크 리스트 사진 필수 여부
    private boolean isComplete; //체크 리스트 완료 여부
    private String memo; //체크 리스트 완료 시 메모

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isComplete() {
        return isComplete;
    }


    public void setCheckListManagers(Set<CheckListManager> checkListManagers) {
        this.checkListManagers.clear();
        if (checkListManagers != null) {
            this.checkListManagers.addAll(checkListManagers);
            checkListManagers.forEach(checkListManager -> checkListManager.setCheckList(this));
        }
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @ManyToOne
    private Staff staff;
    @OneToOne(mappedBy = "checkList")
    private Photo photo;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "checkList", cascade = CascadeType.REMOVE)
    private Set<CheckListManager> checkListManagers;

}
