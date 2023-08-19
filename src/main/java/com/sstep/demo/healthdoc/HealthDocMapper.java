package com.sstep.demo.healthdoc;

import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.healthdoc.dto.HealthDocRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthDocMapper {
    HealthDoc toDocumentEntity(HealthDocRequestDto healthDocRequestDto);
}
