package com.sajan.pms.service;

import com.sajan.pms.dto.CategoryRequest;
import com.sajan.pms.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> addCategory(Category category);
    Optional<List<Category>> getAllCategory();
    Optional<Category> getCategoryById(Integer categoryId);
    Optional<Category> updateCategoryById(Integer categoryId, Category category);
    Optional<Category> deleteCategoryById(Integer categoryId);
}
