package com.jonathan.library.api.resource;

import com.jonathan.library.api.dto.StatusResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {

        return Response.ok()
                .entity(new StatusResponse("UP"))
                .build();

    }

}