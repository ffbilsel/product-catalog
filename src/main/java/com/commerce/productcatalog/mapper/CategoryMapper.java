package com.commerce.productcatalog.mapper;

import com.commerce.productcatalog.model.dto.CategoryDto;
import com.commerce.productcatalog.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);
}
