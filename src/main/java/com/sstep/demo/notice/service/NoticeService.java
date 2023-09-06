package com.sstep.demo.notice.service;

import com.sstep.demo.notice.NoticeRepository;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.notice.dto.NoticeResponseDto;
import com.sstep.demo.photo.PhotoRepository;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final StaffRepository staffRepository;
    private final PhotoRepository photoRepository;
    private final StaffService staffService;


    public void saveNotice(Long staffId, NoticeRequestDto noticeRequestDto) {
        Staff staff = getStaffById(staffId);

        Set<Photo> photo = new HashSet<>();
        for (long num : noticeRequestDto.getPhotoId()) {
            Photo findPhoto = photoRepository.findById(num).orElseThrow();
            photo.add(findPhoto);
        }

        Set<Notice> notices = getNoticesByStaffId(staffId);
        // 현재 시간을 얻습니다.
        LocalDateTime now = LocalDateTime.now();
        // 사용할 포맷을 정의합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // LocalDateTime 객체를 문자열로 변환합니다.
        String formattedDateTime = now.format(formatter);

        Notice notice = Notice.builder()
                .id(noticeRequestDto.getId())
                .contents(noticeRequestDto.getContents())
                .hits(noticeRequestDto.getHits())
                .title(noticeRequestDto.getTitle())
                .writeDate(formattedDateTime)
                .build();

        notice.setPhotos(photo);
        notice.setStaff(staff);
        noticeRepository.save(notice);

        for (Photo p : photo) {
            p.setNotice(notice);
            photoRepository.save(p);
        }

        notices.add(notice);
        staff.setNotices(notices);
        staffRepository.save(staff);
    }

    public NoticeResponseDto getNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow();
        Set<PhotoResponseDto> dto = photoToDto(findNotice);

        return NoticeResponseDto.builder()
                .contents(findNotice.getContents())
                .hits(findNotice.getHits())
                .id(findNotice.getId())
                .writeDate(findNotice.getWriteDate())
                .photo(dto)
                .title(findNotice.getTitle())
                .build();
    }

    public Set<NoticeResponseDto> getNotices(Long storeId) {
        Set<Notice> findNotices = noticeRepository.findAllNoticesByStoreId(storeId);
        Set<NoticeResponseDto> notices = new HashSet<>();
        for (Notice n : findNotices) {
            Set<PhotoResponseDto> dto = photoToDto(n);

            NoticeResponseDto notice = NoticeResponseDto.builder()
                    .contents(n.getContents())
                    .hits(n.getHits())
                    .id(n.getId())
                    .writeDate(n.getWriteDate())
                    .photo(dto)
                    .title(n.getTitle())
                    .build();

            notices.add(notice);
        }
        return notices;
    }

    public Staff getStaffById(Long staffId) {
        return staffService.getStaffById(staffId);
    }

    private Set<Notice> getNoticesByStaffId(Long staffId) {
        return noticeRepository.findNoticesByStaffId(staffId);
    }

    private static Set<PhotoResponseDto> photoToDto(Notice findNotice) {
        Set<PhotoResponseDto> photos = new HashSet<>();
        for (Photo p : findNotice.getPhotos()) {
            PhotoResponseDto photo = PhotoResponseDto.builder()
                    .id(p.getId())
                    .build();

            photos.add(photo);
        }
        return photos;
    }
}
