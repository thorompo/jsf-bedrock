package com.example.controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Üdv az API-ról!");
        response.put("status", "success");
        return Response.ok(response).build();
    }
}
