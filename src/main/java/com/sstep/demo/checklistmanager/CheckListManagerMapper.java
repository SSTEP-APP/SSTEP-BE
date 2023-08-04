package com.sstep.demo.checklistmanager;

import com.sstep.demo.checklistmanager.domain.CheckListManager;
import com.sstep.demo.checklistmanager.dto.CheckListManagerRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckListManagerMapper {
    CheckListManager toCheckListManagerEntity(CheckListManagerRequestDto managerRequestDto);
}
