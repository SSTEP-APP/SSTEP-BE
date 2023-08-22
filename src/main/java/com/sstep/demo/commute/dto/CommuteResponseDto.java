package com.sstep.demo.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommuteResponseDto {
    private long staffId; //직원 고유 번호
    private String staffName; //직원 이름
    private long id; //실 출퇴근시간 고유번호
    private String commuteDate; //출퇴근 일자
    private DayOfWeek dayOfWeek; //출퇴근 요일
    private String startTime; //출근 시간
    private String endTime; //퇴근 시간
    private boolean isLate; //지각 여부
    private String disputeMessage; //출퇴근 관련 이의 신청 메시지
    private String disputeStartTime; //정정 출근 시간
    private String disputeEndTime; //정정 퇴근 시간
}
