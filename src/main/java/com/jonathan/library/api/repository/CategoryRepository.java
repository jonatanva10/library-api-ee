package com.jonathan.library.api.repository;

import com.jonathan.library.api.entity.Category;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class CategoryRepository {

    private final ConcurrentHashMap<Long, Category> database = new ConcurrentHashMap<>();

    private final AtomicLong sequence = new AtomicLong(1);

    public List<Category> findAll() {
        return new ArrayList<>(database.values());
    }

    public Category findById(Long id) {
        return database.get(id);
    }

    public Category save(Category category) {

        Long id = sequence.getAndIncrement();

        category.setId(id);

        database.put(id, category);

        return category;
    }

    public Category update(Long id, Category category) {

        category.setId(id);

        database.put(id, category);

        return category;
    }

    public void delete(Long id) {
        database.remove(id);
    }
}