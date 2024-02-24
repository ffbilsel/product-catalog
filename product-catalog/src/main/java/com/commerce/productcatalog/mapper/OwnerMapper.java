package com.commerce.productcatalog.mapper;

import com.commerce.productcatalog.model.dto.OwnerDto;
import com.commerce.productcatalog.model.entity.Owner;
import org.mapstruct.Mapper;

@Mapper
public interface OwnerMapper {
    Owner ownerDtoToOwner(OwnerDto ownerDto);
    OwnerDto ownerToOwnerDto(Owner owner);

}
