package com.sstep.demo.staff.domain;

import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.weekschedule.domain.WeekSchedule;
import lombok.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import java.sql.Date;
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
    @Column(nullable = false)
    private Date startDay; //입사일
    @Column(nullable = false)
    private int hourMoney; //시급
    @Column(nullable = false)
    private int wageType; //급여 지급 방식 일급(1), 주급(2), 월급(3)
    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    private boolean ownerStatus; //사장 여부

    public Staff(long id, Date startDay, int hourMoney, boolean ownerStatus) {
        this.id = id;
        this.startDay = startDay;
        this.hourMoney = hourMoney;
        this.ownerStatus = ownerStatus;
    }

    //회원 테이블과 1대다 조인
    @ManyToOne
    private Member member;

    //사업장 테이블과 1대다 조인
    @ManyToOne
    private Store store;

    //요일별 근무 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private Set<WeekSchedule> weekSchedules;

    //일자별 실 출퇴근시간 테이블과 다대1 조인
    @OneToMany(mappedBy = "staff", cascade = CascadeType.REMOVE)
    private Set<Commute> commutes;


}
