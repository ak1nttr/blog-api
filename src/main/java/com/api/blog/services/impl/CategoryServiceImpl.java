package com.api.blog.services.impl;


import com.api.blog.domain.entities.Category;
import com.api.blog.repositories.CategoryRepository;
import com.api.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories(){
        return categoryRepository.findAllWithPostCount();
    }
}
