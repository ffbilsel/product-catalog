package com.commerce.productcatalog.controller.rest;

import com.commerce.productcatalog.model.dto.OwnerDto;
import com.commerce.productcatalog.model.entity.Owner;
import com.commerce.productcatalog.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/owner")
@RestController
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public Owner create(@RequestBody OwnerDto ownerDto) {
        return ownerService.createOwner(ownerDto);
    }

    @PutMapping("/{id}")
    public Owner update(@PathVariable String id, @RequestBody OwnerDto ownerDto) {
        return ownerService.updateOwner(id, ownerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        ownerService.deleteOwner(id);
    }

    @GetMapping("/{id}")
    public Owner get(@PathVariable String id) {
        return ownerService.getOwner(id);
    }


    @PostMapping("/page")
    public Page<Owner> getAllByPage(@RequestBody PageRequest pageRequest) {
        return ownerService.getOwners(pageRequest);
    }

    @PostMapping("/sort/{sortBy}/{sortOrder}")
    public void getAllSorted(@PathVariable String sortBy, @PathVariable String sortOrder) {
    ownerService.getOwnersSorted(sortBy, sortOrder);
    }

}
