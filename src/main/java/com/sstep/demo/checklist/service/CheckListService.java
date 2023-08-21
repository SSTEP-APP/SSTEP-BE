package com.sstep.demo.checklist.service;

import com.sstep.demo.category.domain.Category;
import com.sstep.demo.category.dto.CategoryRequestDto;
import com.sstep.demo.checklist.CheckListRepository;
import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.checklist.dto.CheckListRequestDto;
import com.sstep.demo.checklist.dto.CheckListResponseDto;
import com.sstep.demo.checklistmanager.domain.CheckListManager;
import com.sstep.demo.checklistmanager.dto.CheckListManagerRequestDto;
import com.sstep.demo.checklistmanager.service.CheckListManagerService;
import com.sstep.demo.photo.domain.Photo;
import com.sstep.demo.photo.service.PhotoService;
import com.sstep.demo.staff.StaffRepository;
import com.sstep.demo.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CheckListService {
    private final CheckListRepository checkListRepository;
    private final StaffRepository staffRepository;
    private final PhotoService photoService;
    private final CheckListManagerService checkListManagerService;


//    public void saveCheckList(Long staffId, CheckListRequestDto checkListRequestDto) {
//        Staff staff = getStaff(staffId);
//
//        //직원 고유번호로 해당 직원이 작성한 체크 리스트들 가져오기
//        Set<CheckList> checkLists = getCheckListsByStaffId(staffId);
//        CheckList checkList = getCheckListEntity(checkListRequestDto);
//
//        Set<Category> categories = new HashSet<>();
//        for (CategoryRequestDto categoryDto : categoryRequestDto) {
//            Category category = Category.builder()
//                    .name(categoryDto.getName()).build();
//            categories.add(categoryMapper.toCategoryEntity(categoryDto));
//        }
//        checkList.setCategories(categories);
//
//        Set<CheckListManager> checkListManagers = new HashSet<>();
//        for (CheckListManagerRequestDto managerRequestDto : checkListManagerRequestDto) {
//            checkListManagers.add(checkListManagerService.saveManagers(managerRequestDto));
//        }
//        checkList.setCheckListManagers(checkListManagers);
//
//        checkLists.add(checkList);
//        staff.setCheckLists(checkLists);
//        staffRepository.save(staff);
//    }


    public Set<CheckListResponseDto> getCompleteCheckListsByCategory(Long storeId, CategoryRequestDto categoryRequestDto) {
        Set<CheckListResponseDto> checkLists = new HashSet<>();
        for (CheckList findCheckList : checkListRepository.findCheckListByStoreIdAndCategoryAndIsComplete(storeId, categoryRequestDto.getName())) {
            CheckListResponseDto checkList = CheckListResponseDto.builder()
                    .title(findCheckList.getTitle())
                    .contents(findCheckList.getContents())
                    .endDay(findCheckList.getEndDay())
                    .id(findCheckList.getId())
                    .needPhoto(findCheckList.isNeedPhoto())
                    .isComplete(findCheckList.isComplete())
                    .memo(findCheckList.getMemo())
                    .build();
            checkLists.add(checkList);
        }
        return checkLists;
    }

    public Set<CheckListResponseDto> getUnCompletedCheckListsByCategory(Long storeId, CategoryRequestDto categoryRequestDto) {
        Set<CheckListResponseDto> checkLists = new HashSet<>();
        for (CheckList findCheckList : checkListRepository.findCheckListByStoreIdAndCategoryAndIsUnComplete(storeId, categoryRequestDto.getName())) {
            CheckListResponseDto checkList = CheckListResponseDto.builder()
                    .title(findCheckList.getTitle())
                    .contents(findCheckList.getContents())
                    .endDay(findCheckList.getEndDay())
                    .id(findCheckList.getId())
                    .needPhoto(findCheckList.isNeedPhoto())
                    .isComplete(findCheckList.isComplete())
                    .memo(findCheckList.getMemo())
                    .build();
            checkLists.add(checkList);
        }
        return checkLists;
    }

    public CheckListResponseDto getCheckList(Long checklistId) {
        CheckList findCheckList = checkListRepository.findById(checklistId).orElseThrow();
        return CheckListResponseDto.builder()
                .memo(findCheckList.getMemo())
                .id(findCheckList.getId())
                .endDay(findCheckList.getEndDay())
                .contents(findCheckList.getContents())
                .title(findCheckList.getTitle())
                .isComplete(findCheckList.isComplete())
                .needPhoto(findCheckList.isNeedPhoto())
                .build();
    }

    public void completeCheckList(Long checklistId, Long staffId, MultipartFile[] multipartFile, String memo) throws
            IOException {
        Staff staff = getStaff(staffId);

        Set<CheckList> checkLists = getCheckListsByStaffId(staffId);
        CheckList checkList = checkListRepository.findById(checklistId).orElseThrow();
        if (Arrays.stream(multipartFile).findAny().isPresent()) {
            for (MultipartFile imageFile : multipartFile) {
                Set<Photo> photos = new HashSet<>();

                Photo photo = photoService.savePhoto(imageFile);
                photos.add(photo);
                checkList.setPhotos(photos);
            }
        }
        checkList.setMemo(memo);
        checkList.setComplete(true);
        checkLists.add(checkList);
        staff.setCheckLists(checkLists);
        staffRepository.save(staff);
    }

    private Set<CheckList> getCheckListsByStaffId(Long staffId) {
        return checkListRepository.findCheckListsByStaffId(staffId);
    }

    private Staff getStaff(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow();
    }
}
