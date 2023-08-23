package com.sstep.demo.commute;

import com.sstep.demo.commute.dto.CommuteRequestDto;
import com.sstep.demo.commute.dto.CommuteResponseDto;
import com.sstep.demo.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/commute")
public class CommuteController {
    private final CommuteService commuteService;

    //직원별 실시간 출근정보 저장
    @PostMapping("/{staffId}/add-commute")
    public ResponseEntity<Void> registerCommute(@PathVariable Long staffId, @RequestBody CommuteRequestDto commuteRequestDto) throws Exception {
        commuteService.saveCommute(commuteRequestDto, staffId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //직원별 실시간 퇴근정보 저장
    @PostMapping("/{staffId}/{nowDate}/update-commute")
    public ResponseEntity<Void> updateCommute(@PathVariable Long staffId, @PathVariable String nowDate, @RequestBody CommuteRequestDto commuteRequestDto) {
        commuteService.updateCommute(staffId, nowDate, commuteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //출퇴근 정보
    @GetMapping("{staffId}/{date}")
    public CommuteResponseDto getCommute(@PathVariable Long staffId, @PathVariable String date) {
        return commuteService.getCommute(staffId, date);
    }

    //이의 신청 시 메시지 업데이트 + 정정시간 설정
    @PostMapping("/{commuteId}/dispute")
    public ResponseEntity<Void> DisputeCommute(@PathVariable Long commuteId, @RequestBody CommuteRequestDto commuteRequestDto) {
        commuteService.disputeCommute(commuteId, commuteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //사장에게 이의신청 내용 보여주기
    @GetMapping("{commuteId}/dispute")
    public CommuteResponseDto getDispute(@PathVariable Long commuteId) {
        return commuteService.getDispute(commuteId);
    }

    //이의 신청 사항 처리 완료시 메시지 삭제
    @PostMapping("/{staffId}/{commuteId}/update-dispute")
    public ResponseEntity<Void> UpdateDisputeCommute(@PathVariable Long staffId, @PathVariable Long commuteId, @RequestBody CommuteRequestDto commuteRequestDto) {
        commuteService.UpdateDisputeCommute(staffId, commuteId, commuteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //해당 직원의 이의 신청 리스트 가져오기
    @GetMapping("/{storeId}/dispute-list")
    public Set<CommuteResponseDto> getDisputeList(@PathVariable Long storeId) {
        return commuteService.getDisputeList(storeId);
    }
}
