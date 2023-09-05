package com.sstep.demo.checklistmanager.service;

import com.sstep.demo.checklist.CheckListRepository;
import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.checklistmanager.CheckListManagerRepository;
import com.sstep.demo.checklistmanager.domain.CheckListManager;
import com.sstep.demo.checklistmanager.dto.CheckListManagerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CheckListManagerService {
    private final CheckListManagerRepository checkListManagerRepository;
    private final CheckListRepository checkListRepository;


    public void saveManagers(Long checkListId, CheckListManagerRequestDto checkListManagerRequestDto) {
        CheckList checkList = checkListRepository.findById(checkListId).orElseThrow();
        CheckListManager checkListManager = CheckListManager.builder()
                .phoneNum(checkListManagerRequestDto.getPhoneNum())
                .name(checkListManagerRequestDto.getName())
                .build();

        checkListManagerRepository.save(checkListManager);

        Set<CheckListManager> checkListManagers = getCheckListManagerByCheckListId(checkListId);
        checkListManagers.add(checkListManager);
        checkList.setCheckListManagers(checkListManagers);
        checkListRepository.save(checkList);
    }

    private Set<CheckListManager> getCheckListManagerByCheckListId(Long checkListId) {
        return checkListManagerRepository.findCheckListManagersByCheckListId(checkListId);
    }
}
