package com.sstep.demo.notice;

import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    //공지사항 상세 정보 조회
    @GetMapping("{noticeId}/detail")
    public Notice getNotice(@PathVariable Long noticeId) {
        return noticeService.getNotice(noticeId);
    }

    //사업장 내 전체 공지사항 목록 조회
    @GetMapping("/{storeId}/notices")
    public Set<Notice> getNotices(@PathVariable Long storeId) {
        return noticeService.getNotices(storeId);
    }

    //공지사항 등록
    @PostMapping("/{staffId}/add-notice")
    public ResponseEntity<Void> registerNotice(@PathVariable Long staffId,
                                               @RequestBody NoticeRequestDto noticeRequestDto) throws IOException {
        noticeService.saveNotice(staffId, noticeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
