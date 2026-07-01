package com.jonathan.library.api.service;

import com.jonathan.library.api.entity.Book;
import com.jonathan.library.api.exception.BookNotFoundException;
import com.jonathan.library.api.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    private BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        Book book = repository.findById(id);

        if (book == null) {
            throw new BookNotFoundException(id);
        }

        return book;
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Long id, Book book) {

        validateBook(book);

        getBookById(id);

        return repository.update(id, book);
    }

    public void deleteBook(Long id) {

        getBookById(id);

        repository.delete(id);
    }

    private void validateBook(Book book) {

        if (book == null) {
            throw new IllegalArgumentException("Book is required.");
        }

        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new IllegalArgumentException("Book title is required.");
        }

        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            throw new IllegalArgumentException("Book author is required.");
        }

        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            throw new IllegalArgumentException("Book ISBN is required.");
        }
    }
}