package fr.miage.m1.sntp.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

@QuarkusTest
public class LigneDeTrainRessourcesTest {

    @Test
    void getLigneDeTrainsTest()
    {
        given()
                .when()
                .get("/ligneDeTrains")
                .then()
                .body("nomLigne", hasItems("Paris-Marseille", "Paris-Bordeaux"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getLigneDeTrainTest()
    {
        Integer current = 2;
        given()
                .when()
                .pathParam("id", current)
                .get("/ligneDeTrains/ligneDeTrain/{id}").then()
                .body("nomLigne", equalTo("Paris-Bordeaux"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

}
