package com.sstep.demo.category;

import com.sstep.demo.category.domain.Category;
import com.sstep.demo.category.dto.CategoryRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategoryEntity(CategoryRequestDto categoryRequestDto);
}
