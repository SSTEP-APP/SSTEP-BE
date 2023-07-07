package com.sstep.demo.store;

import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.staff.dto.StaffRequestDto;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.dto.StoreRequestDto;
import com.sstep.demo.store.dto.StoreResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store toEntity(StoreRequestDto storeRequestDto);

    StoreResponseDto EntityToResponseDto(Store store);

    StoreRequestDto ResponseToRequestDto(StoreRequestDto  storeRequestDto);

    Staff toStaffEntity(StaffRequestDto staffRequestDto);
}
