package com.jonathan.library.api.service;

import com.jonathan.library.api.entity.Book;
import com.jonathan.library.api.exception.BookNotFoundException;
import com.jonathan.library.api.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    void shouldReturnBookById() {

        Book book =
                new Book(1L,
                        "Clean Code",
                        "Robert C. Martin",
                        "9780132350884");

        when(repository.findById(1L))
                .thenReturn(book);

        Book result = service.getBookById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());
        assertEquals("9780132350884", result.getIsbn());

        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenBookDoesNotExist() {

        when(repository.findById(100L))
                .thenReturn(null);

        assertThrows(
                BookNotFoundException.class,
                () -> service.getBookById(100L)
        );

        verify(repository).findById(100L);
    }

    @Test
    void shouldCreateBook() {

        Book book =
                new Book(null,
                        "Clean Code",
                        "Robert C. Martin",
                        "9780132350884");

        Book saved =
                new Book(1L,
                        "Clean Code",
                        "Robert C. Martin",
                        "9780132350884");

        when(repository.save(book))
                .thenReturn(saved);

        Book result = service.createBook(book);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());
        assertEquals("9780132350884", result.getIsbn());

        verify(repository).save(book);
    }

    @Test
    void shouldUpdateBook() {

        Book existing =
                new Book(1L,
                        "Old Title",
                        "Old Author",
                        "1111111111111");

        Book updated =
                new Book(1L,
                        "Clean Architecture",
                        "Robert C. Martin",
                        "9780134494166");

        when(repository.findById(1L))
                .thenReturn(existing);

        when(repository.update(1L, updated))
                .thenReturn(updated);

        Book result = service.updateBook(1L, updated);

        assertEquals("Clean Architecture", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());
        assertEquals("9780134494166", result.getIsbn());

        verify(repository).findById(1L);
        verify(repository).update(1L, updated);
    }

    @Test
    void shouldDeleteBook() {

        Book existing =
                new Book(1L,
                        "Clean Code",
                        "Robert C. Martin",
                        "9780132350884");

        when(repository.findById(1L))
                .thenReturn(existing);

        service.deleteBook(1L);

        verify(repository).findById(1L);
        verify(repository).delete(1L);
    }
}