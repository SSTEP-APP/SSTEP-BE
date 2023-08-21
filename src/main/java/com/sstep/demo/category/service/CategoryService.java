package com.sstep.demo.category.service;

import com.sstep.demo.category.CategoryRepository;
import com.sstep.demo.category.domain.Category;
import com.sstep.demo.category.dto.CategoryRequestDto;
import com.sstep.demo.category.dto.CategoryResponseDto;
import com.sstep.demo.store.StoreRepository;
import com.sstep.demo.store.domain.Store;
import com.sstep.demo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final StoreService storeService;

    public void saveCategory(Long storeId, CategoryRequestDto categoryRequestDto) {
        Store store = getStore(storeId);

        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .store(store)
                .build();
        categoryRepository.save(category);

        Set<Category> categories = getAllCategoriesByStoreId(storeId);
        categories.add(category);
        store.setCategories(categories);
        storeRepository.save(store);
    }

    private Store getStore(Long storeId) {
        return storeService.getStore(storeId);
    }

    public Set<Category> getAllCategoriesByStoreId(Long storeId) {
        return categoryRepository.findCategoriesByStoreId(storeId);
    }

    public Set<CategoryResponseDto> getCategories(Long storeId) {
        Set<CategoryResponseDto> category = new HashSet<>();
        for (Category findCategory : getAllCategoriesByStoreId(storeId)) {
            CategoryResponseDto c = CategoryResponseDto.builder()
                    .id(findCategory.getId())
                    .name(findCategory.getName())
                    .build();
            category.add(c);
        }
        return category;
    }
}
