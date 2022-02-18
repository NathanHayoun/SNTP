package fr.miage.m1.sntp.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.*;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TrainRessourcesTest {

    @Test
    void getTrainsTest()
    {
        given()
                .when()
                .get("/trains")
                .then()
                .body("stationDepart", hasItems("Paris", "Bordeaux"))
                .body("terminus", hasItems("Marseille", "Nancy"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getTrainTest()
    {
        given()
                .when()
                .get("/trains/train/1")
                .then()
                .body("id", equalTo(1))
                .body("stationDepart", equalTo("Paris"))
                .body("terminus", equalTo("Marseille"))
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
