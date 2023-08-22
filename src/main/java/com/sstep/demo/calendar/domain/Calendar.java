package com.sstep.demo.calendar.domain;

import com.sstep.demo.staff.domain.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Calendar {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //일정 고유번호
    private String calendarDate; //일자
    private DayOfWeek dayOfWeek; //요일
    private String startCalTime;
    private String endCalTime;


    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @ManyToOne
    private Staff staff;
}
