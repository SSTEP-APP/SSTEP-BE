package com.sstep.demo.checklist.service;

import com.sstep.demo.category.CategoryRepository;
import com.sstep.demo.category.domain.Category;
import com.sstep.demo.checklist.CheckListRepository;
import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.checklist.dto.CheckListRequestDto;
import com.sstep.demo.checklist.dto.CheckListResponseDto;
import com.sstep.demo.photo.PhotoRepository;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.dto.PhotoResponseDto;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CheckListService {
    private final CheckListRepository checkListRepository;
    private final StaffRepository staffRepository;
    private final PhotoRepository photoRepository;
    private final CategoryRepository categoryRepository;

    public Long saveCheckList(Long staffId, CheckListRequestDto checkListRequestDto) {
        Staff staff = getStaff(staffId);
        Category c = categoryRepository.findById(checkListRequestDto.getCategoryId()).orElseThrow();

        Set<CheckList> checkLists = getCheckListsByStaffId(staffId);
        CheckList checkList = CheckList.builder()
                .contents(checkListRequestDto.getContents())
                .endDay(checkListRequestDto.getEndDay())
                .isComplete(checkListRequestDto.isComplete())
                .memo(checkListRequestDto.getMemo())
                .title(checkListRequestDto.getTitle())
                .date(checkListRequestDto.getDate())
                .needPhoto(checkListRequestDto.isNeedPhoto())
                .build();

        checkList.setCategory(c);
        checkList.setStaff(staff);
        checkListRepository.save(checkList);

        Set<CheckList> findCheckList = getCheckListsByCategoryId(c.getId());
        findCheckList.add(checkList);
        c.setCheckLists(findCheckList);
        categoryRepository.save(c);

        checkLists.add(checkList);
        staff.setCheckLists(checkLists);
        staffRepository.save(staff);

        return checkList.getId();
    }

    public void completeCheckList(Long staffId, Long checklistId, CheckListRequestDto checkListRequestDto) {
        Staff staff = getStaff(staffId);
        Photo p = photoRepository.findById(checkListRequestDto.getPhotoId()).orElseThrow();

        Set<CheckList> checkLists = getCheckListsByStaffId(staffId);
        CheckList checkList = checkListRepository.findById(checklistId).orElseThrow();

        p.setCheckList(checkList);
        photoRepository.save(p);

        checkList.setPhoto(p);
        checkList.setMemo(checkListRequestDto.getMemo());
        checkList.setComplete(true);
        checkListRepository.save(checkList);

        checkLists.add(checkList);
        staff.setCheckLists(checkLists);
        staffRepository.save(staff);
    }

    public CheckListResponseDto getCheckList(Long checklistId) {
        Set<PhotoResponseDto> photos = new HashSet<>();
        for (Photo photo : photoRepository.findPhotosByCheckListId(checklistId)) {
            PhotoResponseDto p = PhotoResponseDto.builder()
                    .id(photo.getId())
                    .fileName(photo.getFileName())
                    .contentType(photo.getContentType())
                    .data(photo.getData())
                    .build();

            photos.add(p);
        }


        CheckList findCheckList = checkListRepository.findById(checklistId).orElseThrow();
        return CheckListResponseDto.builder()
                .memo(findCheckList.getMemo())
                .id(findCheckList.getId())
                .endDay(findCheckList.getEndDay())
                .contents(findCheckList.getContents())
                .title(findCheckList.getTitle())
                .isComplete(findCheckList.isComplete())
                .needPhoto(findCheckList.isNeedPhoto())
                .photoResponseDto(photos)
                .build();
    }

    public Set<CheckListResponseDto> getCompleteCheckListsByCategory(Long staffId, CheckListRequestDto checkListRequestDto) {
        Set<CheckListResponseDto> checkLists = new HashSet<>();
        for (CheckList findCheckList : checkListRepository.findCheckListByStaffIdAndCategoryIdAndIsCompleteAndDate(staffId, checkListRequestDto.getCategoryId(), checkListRequestDto.getDate())) {
            CheckListResponseDto checkList = CheckListResponseDto.builder()
                    .title(findCheckList.getTitle())
                    .contents(findCheckList.getContents())
                    .categoryId(findCheckList.getCategory().getId())
                    .endDay(findCheckList.getEndDay())
                    .id(findCheckList.getId())
                    .date(findCheckList.getDate())
                    .needPhoto(findCheckList.isNeedPhoto())
                    .isComplete(findCheckList.isComplete())
                    .memo(findCheckList.getMemo())
                    .build();

            checkLists.add(checkList);
        }
        return checkLists;
    }

    public Set<CheckListResponseDto> getUnCompletedCheckListsByCategory(Long staffId, CheckListRequestDto checkListRequestDto) {
        Set<CheckListResponseDto> checkLists = new HashSet<>();
        for (CheckList findCheckList : checkListRepository.findCheckListByStaffIdAndCategoryIdAndIsUnCompleteAndDate(staffId, checkListRequestDto.getCategoryId(), checkListRequestDto.getDate())) {
            CheckListResponseDto checkList = CheckListResponseDto.builder()
                    .title(findCheckList.getTitle())
                    .contents(findCheckList.getContents())
                    .endDay(findCheckList.getEndDay())
                    .categoryId(findCheckList.getCategory().getId())
                    .id(findCheckList.getId())
                    .needPhoto(findCheckList.isNeedPhoto())
                    .isComplete(findCheckList.isComplete())
                    .memo(findCheckList.getMemo())
                    .build();
            checkLists.add(checkList);
        }
        return checkLists;
    }


    private Set<CheckList> getCheckListsByStaffId(Long staffId) {
        return checkListRepository.findCheckListsByStaffId(staffId);
    }

    private Staff getStaff(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow();
    }

    private Set<CheckList> getCheckListsByCategoryId(long id) {
        return checkListRepository.findCheckListsByCategoryId(id);
    }
}
