package com.sstep.demo.notice.service;

import com.sstep.demo.notice.NoticeRepository;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final PhotoService photoService;

    public void saveNotice(Notice notice, MultipartFile imageFile) throws IOException {
        List<Photo> photos = getPhotosByNoticeId(notice.getId());

        if (imageFile != null && !imageFile.isEmpty()) {
            Photo photo = photoService.savePhoto(imageFile);
            photos.add(photo);
            notice.setPhotos(photos);
        }
        notice.setWriteDate(LocalDateTime.now()); // 공지사항 작성일을 현재 시간으로 설정
        noticeRepository.save(notice);
    }

    private List<Photo> getPhotosByNoticeId(long id) {
        return noticeRepository.findPhotosById(id);
    }

    public Notice getNotice(Long noticeId) {
        return noticeRepository.findNoticeById(noticeId);
    }
}
