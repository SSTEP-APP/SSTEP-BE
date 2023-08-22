package com.sstep.demo.staff.domain;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.workdoc.domain.WorkDoc;
import lombok.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import java.time.LocalDate;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Staff {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //직원 고유번호
    private LocalDate startDay; //입사일
    private int hourMoney; //시급
    private int wageType; //급여 지급 방식 일급(1), 주급(2), 월급(3)
    private String paymentDate; //급여지급일
    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    private boolean ownerStatus; //사장 여부
    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    private boolean joinStatus; //초대 여부, 초대를 보냈으면 true
    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    private boolean submitStatus; //코드 입력 여부, 코드를 입력했으면 true

    public void setStore(Store store) {
        this.store = store;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setHourMoney(int hourMoney) {
        this.hourMoney = hourMoney;
    }

    public void setWageType(int wageType) {
        this.wageType = wageType;
    }

    public void setSubmitStatus(boolean submitStatus) {
        this.submitStatus = submitStatus;
    }

    public void setJoinStatus(boolean joinStatus) {
        this.joinStatus = joinStatus;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules.clear();
        if (schedules != null) {
            this.schedules.addAll(schedules);
            schedules.forEach(schedule -> schedule.setStaff(this));
        }
    }

    public void setCommutes(Set<Commute> commutes) {
        this.commutes.clear();
        if (commutes != null) {
            this.commutes.addAll(commutes);
            commutes.forEach(commute -> commute.setStaff(this));
        }
    }

    public void setCalendars(Set<Calendar> calendars) {
        this.calendars.clear();
        if (calendars != null) {
            this.calendars.addAll(calendars);
            calendars.forEach(calendar -> calendar.setStaff(this));
        }
    }

    public void setNotices(Set<Notice> notices) {
        this.notices.clear();
        if (notices != null) {
            this.notices.addAll(notices);
            notices.forEach(notice -> notice.setStaff(this));
        }
    }

    public void setCheckLists(Set<CheckList> checkLists) {
        this.checkLists.clear();
        if (checkLists != null) {
            this.checkLists.addAll(checkLists);
            checkLists.forEach(checkList -> checkList.setStaff(this));
        }
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setHealthDoc(HealthDoc healthDoc) {
        this.healthDoc = healthDoc;
    }

    public void setWorkDoc(WorkDoc workDoc) {
        this.workDoc = workDoc;
    }

    //회원 테이블과 1대다 조인
    @ManyToOne
    private Member member;

    //사업장 테이블과 1대다 조인
    @ManyToOne
    private Store store;

    //요일별 근무 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private Set<Schedule> schedules;

    //일자별 실 출퇴근시간 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private Set<Commute> commutes;

    //캘린더 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private Set<Calendar> calendars;

    //공지사항 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private Set<Notice> notices;

    //체크 리스트 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private Set<CheckList> checkLists;

    //보건증 테이블과 1대1조인
    @OneToOne
    private HealthDoc healthDoc;

    //근로 계약서 테이블과 1대1조인
    @OneToOne
    private WorkDoc workDoc;
}
