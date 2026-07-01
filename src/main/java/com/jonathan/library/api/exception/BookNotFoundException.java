package com.jonathan.library.api.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Category with id " + id + " was not found.");
    }
}
