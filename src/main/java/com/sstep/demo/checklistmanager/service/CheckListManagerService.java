package com.sstep.demo.checklistmanager.service;

import com.sstep.demo.checklistmanager.CheckListManagerMapper;
import com.sstep.demo.checklistmanager.domain.CheckListManager;
import com.sstep.demo.checklistmanager.dto.CheckListManagerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckListManagerService {
    private final CheckListManagerMapper checkListManagerMapper;

    public CheckListManager saveManagers(CheckListManagerRequestDto managerRequestDto) {
        return getCheckListManagerEntity(managerRequestDto);
    }

    private CheckListManager getCheckListManagerEntity(CheckListManagerRequestDto managerRequestDto) {
        return checkListManagerMapper.toCheckListManagerEntity(managerRequestDto);
    }
}
