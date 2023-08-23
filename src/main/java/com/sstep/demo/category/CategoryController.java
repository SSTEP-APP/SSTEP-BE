package com.sstep.demo.category;

import com.sstep.demo.category.dto.CategoryResponseDto;
import com.sstep.demo.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //카테고리 목록 조회
    @GetMapping("/{storeId}/categories")
    public Set<CategoryResponseDto> getCategories(@PathVariable Long storeId) {
        return categoryService.getCategories(storeId);
    }
}
