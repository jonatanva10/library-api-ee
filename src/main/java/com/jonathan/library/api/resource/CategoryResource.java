package com.jonathan.library.api.resource;

import com.jonathan.library.api.entity.Category;
import com.jonathan.library.api.service.CategoryService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    private CategoryService service;

    @GET
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @POST
    public Category createCategory(Category category) {
        return service.createCategory(category);
    }

    @GET
    @Path("/{id}")
    public Category getCategoryById(@PathParam("id")Long id) {
        return service.getCategoryById(id);
    }

    @PUT
    @Path("/{id}")
    public Category updateCategory(@PathParam("id") Long id,
                                   Category category) {

        return service.updateCategory(id, category);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") Long id) {

        service.deleteCategory(id);

        return Response.noContent().build();
    }
}