package com.sstep.demo.commute.service;

import com.sstep.demo.commute.CommuteRepository;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.commute.dto.CommuteRequestDto;
import com.sstep.demo.commute.dto.CommuteResponseDto;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommuteService {
    private final CommuteRepository commuteRepository;
    private final StaffRepository staffRepository;
    private final StaffService staffService;

    public void saveCommute(CommuteRequestDto commuteRequestDto, Long staffId) {
        Staff staff = staffService.getStaffById(staffId);
        boolean late = isLate(commuteRequestDto, staff.getSchedules());

        Commute commute = Commute.builder()
                .commuteDate(commuteRequestDto.getCommuteDate())
                .dayOfWeek(commuteRequestDto.getDayOfWeek())
                .disputeEndTime(commuteRequestDto.getDisputeEndTime())
                .disputeMessage(commuteRequestDto.getDisputeMessage())
                .disputeStartTime(commuteRequestDto.getDisputeStartTime())
                .endTime(commuteRequestDto.getDisputeEndTime())
                .isLate(late)
                .startTime(commuteRequestDto.getStartTime())
                .staff(staff)
                .build();

        commuteRepository.save(commute);

        Set<Commute> commutes = getCommutesByStaffId(staffId);
        commutes.add(commute);
        staff.setCommutes(commutes);
        staffRepository.save(staff);
    }

    private boolean isLate(CommuteRequestDto commuteRequestDto, Set<Schedule> schedules) { //지각 여부 확인
        //해당 직원의 해당 날짜의 출근 시간 가져와서 비교
        //기준 시간 10분 이후부터 지각 처리
        for (Schedule schedule : schedules) {
            if (schedule.getWeekDay() == commuteRequestDto.getDayOfWeek()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime commuteLocalTime = LocalTime.parse(commuteRequestDto.getStartTime(), formatter);
                LocalTime scheduleLocalTime = LocalTime.parse(schedule.getStartTime(), formatter);
                int commuteMinute = commuteLocalTime.getHour() * 60 + commuteLocalTime.getMinute();
                int scheduleMinute = scheduleLocalTime.getHour() * 60 + scheduleLocalTime.getMinute();
                if (commuteMinute - scheduleMinute >= 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateCommute(Long staffId, LocalDate nowDate, CommuteRequestDto commuteRequestDto) {
        Commute existingCommute = getCommuteByStaffIdAndDate(staffId, nowDate);
        existingCommute.setEndTime(commuteRequestDto.getEndTime());
        commuteRepository.save(existingCommute);
    }

    private Commute getCommuteByStaffIdAndDate(Long staffId, LocalDate date) {
        return commuteRepository.findByStaffIdAndDate(staffId, date);
    }

    public void disputeCommute(Long commuteId, CommuteRequestDto commuteRequestDto) {
        Commute existingCommute = commuteRepository.findById(commuteId).orElseThrow();

        //출퇴근 정보가 이미 존재하면 이의 신청 메시지 업데이트
        existingCommute.setDisputeMessage(commuteRequestDto.getDisputeMessage());
        existingCommute.setDisputeStartTime(commuteRequestDto.getDisputeStartTime());
        existingCommute.setDisputeEndTime(commuteRequestDto.getDisputeEndTime());
    }

    public void UpdateDisputeCommute(Long staffId, Long commuteId, CommuteRequestDto commuteRequestDto) {
        Commute existingCommute = commuteRepository.findByIdAndStoreId(staffId, commuteId);

        //출퇴근 정보가 이미 존재하면 이의 신청 메시지 null로 업데이트
        existingCommute.setDisputeMessage(null);
        existingCommute.setStartTime(commuteRequestDto.getDisputeStartTime());
        existingCommute.setEndTime(commuteRequestDto.getDisputeEndTime());
    }

    public Set<CommuteResponseDto> getDisputeList(Long storeId) {
        Set<CommuteResponseDto> disputeList = new HashSet<>();

        for (Commute commute : commuteRepository.findDisputeListByStoreIdAndMessageIsNotNull(storeId)) {
            CommuteResponseDto dto = CommuteResponseDto.builder()
                    .staffId(commute.getStaff().getId())
                    .staffName(commute.getStaff().getMember().getName())
                    .id(commute.getId())
                    .commuteDate(commute.getCommuteDate())
                    .dayOfWeek(commute.getDayOfWeek())
                    .startTime(commute.getStartTime())
                    .endTime(commute.getEndTime())
                    .isLate(commute.isLate())
                    .disputeMessage(commute.getDisputeMessage())
                    .disputeStartTime(commute.getDisputeStartTime())
                    .disputeEndTime(commute.getDisputeEndTime())
                    .build();

            disputeList.add(dto);
        }

        return disputeList;
    }

    private Set<Commute> getCommutesByStaffId(Long staffId) {
        return commuteRepository.findCommutesByStaffId(staffId);
    }

    public CommuteResponseDto getDispute(Long commuteId) {
        Commute findCommute = commuteRepository.findById(commuteId).orElseThrow();
        return CommuteResponseDto.builder()
                .id(commuteId)
                .commuteDate(findCommute.getCommuteDate())
                .dayOfWeek(findCommute.getDayOfWeek())
                .startTime(findCommute.getStartTime())
                .endTime(findCommute.getEndTime())
                .isLate(findCommute.isLate())
                .disputeMessage(findCommute.getDisputeMessage())
                .disputeStartTime(findCommute.getDisputeStartTime())
                .disputeEndTime(findCommute.getDisputeEndTime())
                .build();
    }

    public CommuteResponseDto getCommute(Long staffId, LocalDate date) {
        Commute findCommute = getCommuteByStaffIdAndDate(staffId, date);

        return CommuteResponseDto.builder()
                .id(findCommute.getId())
                .commuteDate(findCommute.getCommuteDate())
                .dayOfWeek(findCommute.getDayOfWeek())
                .startTime(findCommute.getStartTime())
                .endTime(findCommute.getEndTime())
                .isLate(findCommute.isLate())
                .disputeMessage(findCommute.getDisputeMessage())
                .disputeStartTime(findCommute.getDisputeStartTime())
                .disputeEndTime(findCommute.getDisputeEndTime())
                .build();
    }
}
