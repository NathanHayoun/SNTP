package fr.miage.m1.sntp.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

/**
 * @author Sylvain
 */
@QuarkusTest
public class TicketRessourceTest {

    @Test
    void getTicketsTest()
    {
        given()
                .when()
                .get("/tickets")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getTicketTest()
    {
        given()
                .when()
                .get("/tickets/ticket/95").then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

}
