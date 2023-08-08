package com.sstep.demo.healthdoc.service;

import com.sstep.demo.healthdoc.HealthDocMapper;
import com.sstep.demo.healthdoc.HealthDocRepository;
import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.healthdoc.dto.HealthDocRequestDto;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class HealthDocService {

    private final StaffRepository staffRepository;
    private final HealthDocRepository healthDocRepository;
    private final HealthDocMapper healthDocMapper;
    private final PhotoService photoService;

    public void saveHealthDoc(Long staffId, HealthDocRequestDto healthDocRequestDto, MultipartFile multipartFile) throws IOException {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        HealthDoc healthDoc = getDocumentEntity(healthDocRequestDto);
        if (!multipartFile.isEmpty()) {
            Photo photo = photoService.savePhoto(multipartFile);
            healthDoc.setPhoto(photo);
        }
        staff.setHealthDoc(healthDoc);
        staffRepository.save(staff);
    }

    private HealthDoc getDocumentEntity(HealthDocRequestDto healthDocRequestDto) {
        return healthDocMapper.toDocumentEntity(healthDocRequestDto);
    }

    public HealthDoc getHealthDoc(Long staffId) {
        return healthDocRepository.findByStaffId(staffId);
    }
}
