package com.food.api.mapper;

import com.food.api.domain.Vendor;
import com.food.api.dto.VendorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorDTO toDto(Vendor vendor);

    Vendor toEntity(VendorDTO vendorDTO);
}
