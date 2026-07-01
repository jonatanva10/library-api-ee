package com.jonathan.library.api.service;

import com.jonathan.library.api.entity.Category;
import com.jonathan.library.api.exception.CategoryNotFoundException;
import com.jonathan.library.api.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @Test
    void shouldReturnCategoryById() {

        Category category =
                new Category(1L, "Programming", "Books about programming");

        when(repository.findById(1L))
                .thenReturn(category);

        Category result = service.getCategoryById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Programming", result.getName());
        assertEquals("Books about programming", result.getDescription());

        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenCategoryDoesNotExist() {

        when(repository.findById(100L))
                .thenReturn(null);

        assertThrows(
                CategoryNotFoundException.class,
                () -> service.getCategoryById(100L)
        );

        verify(repository).findById(100L);
    }

    @Test
    void shouldCreateCategory() {

        Category category =
                new Category(null, "Programming", "Books about programming");

        Category saved =
                new Category(1L, "Programming", "Books about programming");

        when(repository.save(category))
                .thenReturn(saved);

        Category result = service.createCategory(category);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Programming", result.getName());
        assertEquals("Books about programming", result.getDescription());

        verify(repository).save(category);
    }

    @Test
    void shouldUpdateCategory() {

        Category existing =
                new Category(1L, "Programming", "Books");

        Category updated =
                new Category(1L, "Java", "Advanced Java Books");

        when(repository.findById(1L))
                .thenReturn(existing);

        when(repository.update(1L, updated))
                .thenReturn(updated);

        Category result = service.updateCategory(1L, updated);

        assertEquals("Java", result.getName());
        assertEquals("Advanced Java Books", result.getDescription());

        verify(repository).findById(1L);
        verify(repository).update(1L, updated);
    }

    @Test
    void shouldDeleteCategory() {

        Category existing =
                new Category(1L, "Programming", "Books");

        when(repository.findById(1L))
                .thenReturn(existing);

        service.deleteCategory(1L);

        verify(repository).findById(1L);
        verify(repository).delete(1L);
    }
}