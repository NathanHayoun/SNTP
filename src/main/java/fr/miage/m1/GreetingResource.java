package fr.miage.m1;

import libs.LibSQL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LibSQL lib = new LibSQL<Object>();
        lib.executeSelect("Show variables;");
        return "Hello SNTP";
    }


}