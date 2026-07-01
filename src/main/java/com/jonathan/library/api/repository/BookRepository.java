package com.jonathan.library.api.repository;

import com.jonathan.library.api.entity.Book;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class BookRepository {

    private final ConcurrentHashMap<Long, Book> database = new ConcurrentHashMap<>();

    private final AtomicLong sequence = new AtomicLong(1);

    public List<Book> findAll() {
        return new ArrayList<>(database.values());
    }

    public Book findById(Long id) {
        return database.get(id);
    }

    public Book save(Book Book) {

        Long id = sequence.getAndIncrement();

        Book.setId(id);

        database.put(id, Book);

        return Book;
    }

    public Book update(Long id, Book Book) {

        Book.setId(id);

        database.put(id, Book);

        return Book;
    }

    public void delete(Long id) {
        database.remove(id);
    }
}