package com.sajan.pms.controller;

import com.sajan.pms.model.Category;
import com.sajan.pms.service.implementation.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/admin/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory(){
        return categoryService.getAllCategory().map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId){
        return categoryService.getCategoryById(categoryId).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/admin/{categoryId}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Integer categoryId, @RequestBody Category category){
        return categoryService.updateCategoryById(categoryId, category).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/admin/{categoryId}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Integer categoryId){
        return categoryService.deleteCategoryById(categoryId).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
}
