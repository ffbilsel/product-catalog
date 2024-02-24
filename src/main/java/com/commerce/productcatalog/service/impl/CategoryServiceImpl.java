package com.commerce.productcatalog.service.impl;

import com.commerce.productcatalog.model.dto.CategoryDto;
import com.commerce.productcatalog.model.query.PageQuery;
import com.commerce.productcatalog.service.CategoryService;
import com.commerce.productcatalog.model.entity.Category;
import com.commerce.productcatalog.repository.blocking.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public String getCategoryName(String categoryId) {
        return getCategoryOptional(categoryId).orElse(new Category()).getName();
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category toCreate = new Category();
        mapCategory(categoryDto, toCreate);
        return categoryRepository.save(toCreate);
    }

    @Override
    public Category updateCategory(String id, CategoryDto categoryDto) {
        return getCategoryOptional(id).map(category -> {
            mapCategory(categoryDto, category);
            return categoryRepository.save(category);
        }).orElse(null);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategory(String id) {
        return getCategoryOptional(id).orElse(null);
    }

    private Optional<Category> getCategoryOptional(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page<Category> getCategories(PageQuery pageQuery) {
        return categoryRepository.findAll(PageRequest.of(pageQuery.getOffset(), pageQuery.getSize()));
    }

    @Override
    public List<Category> getCategoriesSorted(String sortBy, String sortOrder) {
        return categoryRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
    }

    private void mapCategory(CategoryDto categoryDto, Category toCreate) {
        toCreate.setName(categoryDto.getName());
        toCreate.setDescription(categoryDto.getDescription());
    }

}
