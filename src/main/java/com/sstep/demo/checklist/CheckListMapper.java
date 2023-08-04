package com.sstep.demo.checklist;

import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.checklist.dto.CheckListRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckListMapper {
    CheckList toCheckListEntity(CheckListRequestDto checkListRequestDto);
}
