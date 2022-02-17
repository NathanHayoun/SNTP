package fr.miage.m1.sntp.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Random;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PassageRessourcesTest {

    @Test
    void getPassagesTest()
    {
        given()
                .when()
                .get("/passages")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void get10prochainsTrajetsDuJourByGareDepartTest()
    {
        Random random = new Random();
        Integer current = random.nextInt(100);
        given()
                .when()
                .pathParam("id", current)
                .get("/passages/gare/depart/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void get10prochainsTrajetsDuJourByGareArriveeTest()
    {
        Random random = new Random();
        Integer current = random.nextInt(100);
        given()
                .when()
                .pathParam("id", current)
                .get("/passages/gare/arrivee/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
