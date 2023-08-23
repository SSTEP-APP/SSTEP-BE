package com.sstep.demo.checklistmanager.service;

import com.sstep.demo.checklist.CheckListRepository;
import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.checklistmanager.CheckListManagerRepository;
import com.sstep.demo.checklistmanager.domain.CheckListManager;
import com.sstep.demo.checklistmanager.dto.CheckListManagerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CheckListManagerService {
    private final CheckListManagerRepository checkListManagerRepository;
    private final CheckListRepository checkListRepository;


    public void saveManagers(Long checkListId, Set<CheckListManagerRequestDto> checkListManagerRequestDto) {
        CheckList checkList = checkListRepository.findById(checkListId).orElseThrow();
        Set<CheckListManager> checkListManagers = new HashSet<>();

        for (CheckListManagerRequestDto findManager : checkListManagerRequestDto) {
            CheckListManager checkListManager = CheckListManager.builder()
                    .id(findManager.getId())
                    .name(findManager.getName())
                    .phoneNum(findManager.getPhoneNum())
                    .build();

            checkListManager.setCheckList(checkList);
            checkListManagerRepository.save(checkListManager);
            checkListManagers.add(checkListManager);
        }
        checkList.setCheckListManagers(checkListManagers);
        checkListRepository.save(checkList);
    }
}
