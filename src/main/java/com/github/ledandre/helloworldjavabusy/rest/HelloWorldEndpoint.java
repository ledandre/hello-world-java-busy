package com.github.ledandre.helloworldjavabusy.rest;


import com.github.ledandre.helloworldjavabusy.domain.SlowRequestHandler;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;


@Path("/hello")
public class HelloWorldEndpoint {

    private SlowRequestHandler slowHandler = SlowRequestHandler.getInstance();

    @GET
    @Produces("text/plain")
    public Response doGet() {
        String response = "Hello from Thorntail!";
        try {
            slowHandler.handle();

        } catch (Exception e) {
            response = e.getMessage();
        }

        return Response.ok(response).build();
    }
}
