package com.sstep.demo.healthdoc.service;

import com.sstep.demo.healthdoc.HealthDocRepository;
import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.healthdoc.dto.HealthDocRequestDto;
import com.sstep.demo.healthdoc.dto.HealthDocResponseDto;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class HealthDocService {

    private final StaffRepository staffRepository;
    private final HealthDocRepository healthDocRepository;
    private final PhotoService photoService;

    public void saveHealthDoc(Long staffId, HealthDocRequestDto healthDocRequestDto) throws IOException {
        Photo photo = new Photo();
        if (!healthDocRequestDto.getMultipartFile().isEmpty()) {
            photo = photoService.savePhoto(healthDocRequestDto.getMultipartFile());
        }

        Staff staff = staffRepository.findById(staffId).orElseThrow();
        HealthDoc healthDoc = HealthDoc.builder()
                .checkUpDate(healthDocRequestDto.getCheckUpDate())
                .expirationDate(healthDocRequestDto.getExpirationDate())
                .isRegister(true)
                .build();

        healthDoc.setPhoto(photo);
        healthDoc.setStaff(staff);
        healthDocRepository.save(healthDoc);

        photo.setHealthDoc(healthDoc);
        staff.setHealthDoc(healthDoc);
        staffRepository.save(staff);
    }

    public HealthDocResponseDto getHealthDoc(Long staffId) {
        Staff staff = staffRepository.findById(staffId).orElseThrow();

        HealthDoc healthDoc = healthDocRepository.findByStaffId(staffId);
        Photo findPhoto = healthDoc.getPhoto();
        PhotoResponseDto photo = PhotoResponseDto.builder()
                .id(findPhoto.getId())
                .fileName(findPhoto.getFileName())
                .data(findPhoto.getData())
                .contentType(findPhoto.getContentType())
                .build();

        return HealthDocResponseDto.builder()
                .photoResponseDto(photo)
                .checkUpDate(healthDoc.getCheckUpDate())
                .expirationDate(healthDoc.getExpirationDate())
                .name(staff.getMember().getName())
                .build();
    }

    public Set<HealthDocResponseDto> getRegHealthDocStaffs(Long storeId) {
        Set<HealthDocResponseDto> staffs = new HashSet<>();
        for (HealthDoc healthDoc : healthDocRepository.findHealthDocsByStoreIdAndRegister(storeId)) {
            HealthDocResponseDto staff = HealthDocResponseDto.builder()
                    .name(healthDoc.getStaff().getMember().getName())
                    .expirationDate(healthDoc.getExpirationDate())
                    .checkUpDate(healthDoc.getCheckUpDate())
                    .build();

            staffs.add(staff);
        }
        return staffs;
    }

    public Set<HealthDocResponseDto> getUnRegHealthDocStaffs(Long storeId) {
        Set<HealthDocResponseDto> staffs = new HashSet<>();
        for (Staff findStaff : healthDocRepository.findStaffsByStoreIdAndUnRegister(storeId)) {
            HealthDocResponseDto staff = HealthDocResponseDto.builder()
                    .name(findStaff.getMember().getName())
                    .build();

            staffs.add(staff);
        }
        return staffs;
    }
}
