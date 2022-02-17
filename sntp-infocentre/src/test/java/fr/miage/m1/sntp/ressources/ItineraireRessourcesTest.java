package fr.miage.m1.sntp.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class ItineraireRessourcesTest {

    @Test
    void getTrainsTest()
    {
        given()
                .when()
                .get("/itineraires")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getitineraireDAOTest()
    {
        Random random = new Random();
        Integer current = random.nextInt();
        given()
                .when()
                .pathParam("id", current)
                .get("/itineraires/itineraire/{id}").then()
                .statusCode(204);
    }

}
