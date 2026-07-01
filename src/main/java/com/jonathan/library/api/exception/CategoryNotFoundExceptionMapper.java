package com.jonathan.library.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jonathan.library.api.dto.ErrorResponse;

@Provider
public class CategoryNotFoundExceptionMapper
        implements ExceptionMapper<CategoryNotFoundException> {

    @Override
    public Response toResponse(CategoryNotFoundException e) {
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