package fr.miage.m1.sntp.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

@QuarkusTest
public class GareRessourcesTest {

    @Test
    void getGaresTest()
    {
        given()
                .when()
                .get("/gares")
                .then()
                .body("nomGare", hasItems("Paris", "Bordeaux"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getGareTest()
    {
        given()
                .when()
                .get("/gares/gare/1").then()
                .body("nomGare", equalTo("Paris"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

}
