package com.commerce.productcatalog.service;

import com.commerce.productcatalog.model.dto.CategoryDto;
import com.commerce.productcatalog.model.entity.Category;
import com.commerce.productcatalog.model.query.PageQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    String getCategoryName(String categoryId);

    Category createCategory(CategoryDto category);
    Category updateCategory(String id, CategoryDto category);
    void deleteCategory(String id);

    Category getCategory(String id);

    Page<Category> getCategories(PageQuery pageQuery);

    List<Category> getCategoriesSorted(String sortBy, String sortOrder);

}
