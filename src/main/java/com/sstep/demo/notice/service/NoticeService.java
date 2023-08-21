package com.sstep.demo.notice.service;

import com.sstep.demo.notice.NoticeRepository;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.notice.dto.NoticeResponseDto;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final StaffRepository staffRepository;
    private final PhotoService photoService;
    private final StaffService staffService;


    public void saveNotice(Long staffId, NoticeRequestDto noticeRequestDto) throws IOException {
        Staff staff = getStaffById(staffId);

        Set<Photo> photos = new HashSet<>();
        if (Arrays.stream(noticeRequestDto.getMultipartFile()).findAny().isPresent()) {
            for (MultipartFile imageFile : noticeRequestDto.getMultipartFile()) {
                if (imageFile != null && !imageFile.isEmpty()) {
                    Photo photo = photoService.savePhoto(imageFile);
                    photos.add(photo);
                }
            }
        }

        Set<Notice> notices = getNoticesByStaffId(staffId);
        Notice notice = Notice.builder()
                .id(noticeRequestDto.getId())
                .contents(noticeRequestDto.getContents())
                .hits(noticeRequestDto.getHits())
                .photos(photos)
                .staff(staff)
                .title(noticeRequestDto.getTitle())
                .writeDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);
        notices.add(notice);
        staff.setNotices(notices);
        staffRepository.save(staff);
    }

    public NoticeResponseDto getNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow();
        Set<PhotoResponseDto> photos = new HashSet<>();
        for (Photo p : findNotice.getPhotos()) {
            PhotoResponseDto photo = PhotoResponseDto.builder()
                    .contentType(p.getContentType())
                    .data(p.getData())
                    .fileName(p.getFileName())
                    .id(p.getId())
                    .build();

            photos.add(photo);
        }

        return NoticeResponseDto.builder()
                .contents(findNotice.getContents())
                .hits(findNotice.getHits())
                .id(findNotice.getId())
                .writeDate(findNotice.getWriteDate())
                .photo(photos)
                .title(findNotice.getTitle())
                .build();
    }

    public Set<NoticeResponseDto> getNotices(Long storeId) {
        Set<Notice> findNotices = noticeRepository.findAllNoticesByStoreId(storeId);
        Set<NoticeResponseDto> notices = new HashSet<>();
        for (Notice n : findNotices) {
            Set<PhotoResponseDto> photos = new HashSet<>();
            for (Photo p : n.getPhotos()) {
                PhotoResponseDto photo = PhotoResponseDto.builder()
                        .contentType(p.getContentType())
                        .data(p.getData())
                        .fileName(p.getFileName())
                        .id(p.getId())
                        .build();

                photos.add(photo);
            }
            NoticeResponseDto notice = NoticeResponseDto.builder()
                    .contents(n.getContents())
                    .hits(n.getHits())
                    .id(n.getId())
                    .writeDate(n.getWriteDate())
                    .photo(photos)
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
}
