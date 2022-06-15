package com.example.application;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("helloWorld/{username}")
@Singleton
public class HelloWorld {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("username") String username) {
        return "Got it! ==> ".concat(username).concat(" ==>").concat(Objects.toString(this));
    }
}
