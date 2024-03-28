package com.sajan.pms.service.implementation;

import com.sajan.pms.model.Category;
import com.sajan.pms.repo.CategoryRepo;
import com.sajan.pms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    @Override
    public Optional<Category> addCategory(Category category) {
        if(category != null) {
            Category savedCategory = categoryRepo.save(category);
            return Optional.of(savedCategory);
        } else
            return Optional.empty();
    }

    @Override
    public Optional<List<Category>> getAllCategory() {
        List<Category> allCategoryList = categoryRepo.findAll();
        return Optional.of(allCategoryList);
    }

    @Override
    public Optional<Category> getCategoryById(Integer categoryId) {
        return categoryRepo.findById(categoryId);
    }

    @Override
    @Transactional
    public Optional<Category> updateCategoryById(Integer categoryId, Category category) {
        Optional<Category> byId = categoryRepo.findById(categoryId);
        if(byId.isPresent()){
            Category updateCategory = byId.get();
            BeanUtils.copyProperties(category, updateCategory, "categoryId","products");
            categoryRepo.save(updateCategory);
            return Optional.of(updateCategory);
        }else
            return Optional.empty();
    }

    @Override
    public Optional<Category> deleteCategoryById(Integer categoryId) {
        Optional<Category> byId = categoryRepo.findById(categoryId);
        if(byId.isPresent()) {
            categoryRepo.delete(byId.get());
            return byId;
        }
        else
            return Optional.empty();

    }
}
