package com.sstep.demo.category;

import com.sstep.demo.category.domain.Category;
import com.sstep.demo.category.dto.CategoryRequestDto;
import com.sstep.demo.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //카테고리 생성
    @PostMapping("/{storeId}/add")
    public ResponseEntity<Void> registerCategory(@PathVariable Long storeId, @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.saveCategory(storeId, categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //카테고리 목록 조회
    @GetMapping("/{storeId}/categories")
    public List<Category> getCategories(@PathVariable Long storeId) {
        return categoryService.getCategories(storeId);
    }
}