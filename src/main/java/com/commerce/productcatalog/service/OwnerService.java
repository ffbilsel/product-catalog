package com.commerce.productcatalog.service;

import com.commerce.productcatalog.model.dto.OwnerDto;
import com.commerce.productcatalog.model.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {

    String getOwnerName(String ownerId);
    Owner createOwner(OwnerDto ownerDto);
    Owner updateOwner(String id, OwnerDto ownerDto);
    void deleteOwner(String id);
    Owner getOwner(String id);
    Page<Owner> getOwners(Pageable pageable);
    List<Owner> getOwnersSorted(String sortBy, String sortOrder);
}
