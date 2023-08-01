package com.sstep.demo.commute.dto;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class CommuteRequestDto {
    private long id; //실 출퇴근시간 고유번호
    private Date commuteDate; //출퇴근 일자
    private DayOfWeek dayOfWeek; //출퇴근 요일
    private LocalTime startTime; //출근 시간
    private LocalTime endTime; //퇴근 시간
    private boolean isLate; //지각 여부
    private String disputeMessage; //출퇴근 관련 이의 신청 메시지

    public String getDisputeMessage() {
        return disputeMessage;
    }

    public Date getCommuteDate() {
        return commuteDate;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isLate() {
        return isLate;
    }
}
