package org.acme.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("rest")
public class Endpoint {

    @Path("hello")
    @GET
    public String hello() {
        return "Hello, World!";
    }
}
