package com.sstep.demo.staff.domain;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.schedule.domain.Schedule;
import lombok.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import java.sql.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Staff {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //직원 고유번호
    private Date startDay; //입사일
    private int hourMoney; //시급
    private int wageType; //급여 지급 방식 일급(1), 주급(2), 월급(3)
    private String paymentDate; //급여지급일
    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    private boolean ownerStatus; //사장 여부
    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    private boolean joinStatus; //합류 여부, 직원이 사업장 번호로 검색해서 찾으면 false에 나머지 값들은 null인 상태로 직원 저장,사장이 수락을 누르면 true로 변경,

    public void setStore(Store store) {
        this.store = store;
    }

    public void setStartDay(Date startDay) {
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

    public void setJoinStatus(boolean joinStatus) {
        this.joinStatus = joinStatus;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules.clear();
        if (schedules != null) {
            this.schedules.addAll(schedules);
            schedules.forEach(schedule -> schedule.setStaff(this));
        }
    }

    public void setCommutes(List<Commute> commutes) {
        this.commutes.clear();
        if (commutes != null) {
            this.commutes.addAll(commutes);
            commutes.forEach(commute -> commute.setStaff(this));
        }
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars.clear();
        if (calendars != null) {
            this.calendars.addAll(calendars);
            calendars.forEach(calendar -> calendar.setStaff(this));
        }
    }

    public void setNotices(List<Notice> notices) {
        this.notices.clear();
        if (notices != null) {
            this.notices.addAll(notices);
            notices.forEach(notice -> notice.setStaff(this));
        }
    }

    //회원 테이블과 1대다 조인
    @ManyToOne
    private Member member;

    //사업장 테이블과 1대다 조인
    @ManyToOne
    private Store store;

    //요일별 근무 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules;

    //일자별 실 출퇴근시간 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private List<Commute> commutes;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private List<Notice> notices;
}
