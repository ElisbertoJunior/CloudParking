package br.com.elisbjr.cloudparking.controllers;

import br.com.elisbjr.cloudparking.entity.Parking;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    private Parking testParking;


   public Parking setTestParking() {
       testParking = new Parking();
       testParking.setLicense("DMS-8898");
       testParking.setState("GO");
       testParking.setModel("Marea");
       testParking.setColor("prata");
       testParking.setEntryDate(LocalDateTime.now());

       return testParking;
   }

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenSaveThenCheckResult() {
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(setTestParking())
                .post("/parking/save")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("DMS-8898"))
                .extract().response().body().prettyPrint();

    }

    @Test
    void whenFindAllCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking/bring-all")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}