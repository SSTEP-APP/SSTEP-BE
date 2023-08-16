package com.sstep.demo.commute;

import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.commute.dto.CommuteRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommuteMapper {
    Commute toCommuteEntity(CommuteRequestDto commuteRequestDto);
}
