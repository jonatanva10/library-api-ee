package com.jonathan.library.api.service;

import com.jonathan.library.api.entity.Category;
import com.jonathan.library.api.exception.CategoryNotFoundException;
import com.jonathan.library.api.repository.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CategoryService {

    @Inject
    private CategoryRepository repository;

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategoryById(Long id) {
        Category category = repository.findById(id);

        if(category == null){
            throw new CategoryNotFoundException(id);
        }

        return category;
    }

    public Category createCategory(Category category) {

        validateCategory(category);

        return repository.save(category);
    }

    public Category updateCategory(Long id, Category category) {

        validateCategory(category);

        getCategoryById(id);

        return repository.update(id, category);
    }

    public void deleteCategory(Long id) {

        getCategoryById(id);

        repository.delete(id);
    }

    private void validateCategory(Category category) {

        if (category == null) {
            throw new IllegalArgumentException("Category is required.");
        }

        if (category.getName() == null || category.getName().isBlank()) {
            throw new IllegalArgumentException("Category name is required.");
        }

        if (category.getDescription() == null || category.getDescription().isBlank()) {
            throw new IllegalArgumentException("Category description is required.");
        }
    }
}