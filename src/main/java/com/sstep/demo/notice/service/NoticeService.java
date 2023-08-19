package com.sstep.demo.notice.service;

import com.sstep.demo.notice.NoticeMapper;
import com.sstep.demo.notice.NoticeRepository;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.service.StaffService;
import com.sstep.demo.store.service.StoreService;
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
    private final StoreService storeService;
    private final StaffService staffService;
    private final NoticeMapper noticeMapper;


    public void saveNotice(Long staffId, NoticeRequestDto noticeRequestDto) throws IOException {
        Staff staff = getStaffById(staffId);

        Set<Notice> notices = getNoticesByStaffId(staffId);
        Notice notice = getNoticeEntity(noticeRequestDto);
        if (Arrays.stream(noticeRequestDto.getMultipartFile()).findAny().isPresent()) {
            for (MultipartFile imageFile : noticeRequestDto.getMultipartFile()) {
                Set<Photo> photos = getPhotosByNoticeId(notice.getId());

                if (imageFile != null && !imageFile.isEmpty()) {
                    Photo photo = photoService.savePhoto(imageFile);
                    photos.add(photo);
                    notice.setPhotos(photos);
                }
                notice.setWriteDate(LocalDateTime.now()); // 공지사항 작성일을 현재 시간으로 설정
                noticeRepository.save(notice);
            }
        }
        notices.add(notice);
        staff.setNotices(notices);
        staffRepository.save(staff);
    }

    private Set<Photo> getPhotosByNoticeId(long id) {
        return noticeRepository.findPhotosById(id);
    }

    public Notice getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow();
    }

    public Set<Notice> getNotices(Long storeId) {
        Set<Staff> staffs = getStaffsByStoreId(storeId);
        Set<Notice> notices = new HashSet<>();
        if (staffs != null) {
            for (Staff staff : staffs) {
                if (!staff.getNotices().isEmpty()) {
                    notices.addAll(staff.getNotices());
                }
            }
        }
        return notices;
    }

    private Set<Staff> getStaffsByStoreId(Long storeId) {
        return storeService.getStaffsByStoreId(storeId);
    }

    public Staff getStaffById(Long staffId) {
        return staffService.getStaffById(staffId);
    }

    private Notice getNoticeEntity(NoticeRequestDto noticeRequestDto) {
        return noticeMapper.toNoticeEntity(noticeRequestDto);
    }

    private Set<Notice> getNoticesByStaffId(Long staffId) {
        return noticeRepository.findNoticesByStaffId(staffId);
    }
}
