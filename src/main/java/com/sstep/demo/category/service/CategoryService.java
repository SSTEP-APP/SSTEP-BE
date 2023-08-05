package com.sstep.demo.category.service;

import com.sstep.demo.category.CategoryMapper;
import com.sstep.demo.category.CategoryRepository;
import com.sstep.demo.category.domain.Category;
import com.sstep.demo.category.dto.CategoryRequestDto;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final CategoryMapper categoryMapper;

    public void saveCategory(Long storeId, CategoryRequestDto categoryRequestDto) {
        Store store = storeRepository.findById(storeId).orElseThrow();

        List<Category> categories = getAllCategoriesByStoreId(storeId);
        Category category = getCategoryEntity(categoryRequestDto);
        categories.add(category);
        store.setCategories(categories);
    }

    private Category getCategoryEntity(CategoryRequestDto categoryRequestDto) {
        return categoryMapper.toCategoryEntity(categoryRequestDto);
    }

    public List<Category> getAllCategoriesByStoreId(Long storeId) {
        return categoryRepository.findCategoriesByStoreId(storeId);
    }

    public List<Category> getCategories(Long storeId) {
        return getAllCategoriesByStoreId(storeId);
    }
}
