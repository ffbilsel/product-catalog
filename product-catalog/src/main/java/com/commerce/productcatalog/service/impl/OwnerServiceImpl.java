package com.commerce.productcatalog.service.impl;

import com.commerce.productcatalog.model.dto.OwnerDto;
import com.commerce.productcatalog.model.entity.Owner;
import com.commerce.productcatalog.service.OwnerService;
import com.commerce.productcatalog.repository.blocking.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public String getOwnerName(String ownerId) {
        return Optional.ofNullable(getOwner(ownerId)).orElse(new Owner()).getName();
    }

    @Override
    public Owner createOwner(OwnerDto ownerDto) {
        Owner toCreate = new Owner();
        mapOwner(ownerDto, toCreate);
        return ownerRepository.save(toCreate);
    }

    @Override
    public Owner updateOwner(String id, OwnerDto ownerDto) {
        return Optional.ofNullable(getOwner(id)).map(owner -> {
            mapOwner(ownerDto, owner);
            return ownerRepository.save(owner);
        }).orElse(null);
    }

    @Override
    public void deleteOwner(String id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner getOwner(String id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Owner> getOwners(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    @Override
    public List<Owner> getOwnersSorted(String sortBy, String sortOrder) {
        return ownerRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
    }


    private void mapOwner(OwnerDto ownerDto, Owner owner) {
        owner.setName(ownerDto.getName());
        owner.setAddress(ownerDto.getAddress());
        owner.setPhone(ownerDto.getPhone());
        owner.setEmail(ownerDto.getEmail());
    }

}
