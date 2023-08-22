package com.sstep.demo.commute.domain;

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
public class Commute { //일자별 실 출근/퇴근 시간 도메인
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //실 출퇴근시간 고유번호
    private String commuteDate; //출퇴근 일자
    private DayOfWeek dayOfWeek; //출퇴근 요일
    private String startTime; //출근 시간
    private String endTime; //퇴근 시간
    private boolean isLate; //지각 여부
    private String disputeMessage; //출퇴근 관련 이의 신청 메시지
    private String disputeStartTime; //정정 출근 시간
    private String disputeEndTime; //정정 퇴근 시간

    public void setDisputeMessage(String lateMessage) {
        this.disputeMessage = lateMessage;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDisputeStartTime(String disputeStartTime) {
        this.disputeStartTime = disputeStartTime;
    }

    public void setDisputeEndTime(String disputeEndTime) {
        this.disputeEndTime = disputeEndTime;
    }

    //직원 테이블과 1대다 조인
    @ManyToOne
    private Staff staff;

}
