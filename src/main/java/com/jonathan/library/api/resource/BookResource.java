package com.jonathan.library.api.resource;

import com.jonathan.library.api.entity.Book;
import com.jonathan.library.api.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookService service;

    @GET
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @POST
    public Book createBook(Book Book) {
        return service.createBook(Book);
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id")Long id) {
        return service.getBookById(id);
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") Long id,
                                   Book Book) {

        return service.updateBook(id, Book);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {

        service.deleteBook(id);

        return Response.noContent().build();
    }
}