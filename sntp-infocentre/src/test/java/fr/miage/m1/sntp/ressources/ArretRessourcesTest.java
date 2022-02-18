package fr.miage.m1.sntp.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Random;

import static io.restassured.RestAssured.given;


import static org.hamcrest.CoreMatchers.*;


/**
 * @author Sylvain
 */
@QuarkusTest
public class ArretRessourcesTest {

    @Test
    void getArretsTest()
    {
        given()
                .when()
                .get("/arrets")
                .then()
                .body("gareConcerner.nomGare", hasItem("Paris"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getArretByNumTrainTest()
    {
        given()
                .when()
                .get("/arrets/arret/train/6103").then()
                .body("train.numeroDeTrain", hasItem(6103))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getArretsDepartByGareTest()
    {
        Random random = new Random();
        Integer current = random.nextInt(100);
        given()
                .when()
                .pathParam("id", current)
                .get("/arrets/arret/depart/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getArretsArriveeByGareTest()
    {
        Random random = new Random();
        Integer current = random.nextInt();
        given()
                .when()
                .pathParam("id", current)
                .get("/arrets/arret/arrivee/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
