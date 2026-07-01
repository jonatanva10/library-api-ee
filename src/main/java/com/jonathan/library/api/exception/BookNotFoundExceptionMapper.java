package com.jonathan.library.api.exception;

import com.jonathan.library.api.dto.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BookNotFoundExceptionMapper
        implements ExceptionMapper<BookNotFoundException> {

    @Override
    public Response toResponse(BookNotFoundException e) {
        ErrorResponse error =
                new ErrorResponse(
                        404,
                        e.getMessage()
                );

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}