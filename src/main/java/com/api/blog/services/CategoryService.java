package com.api.blog.services;

import com.api.blog.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> listCategories();
}
