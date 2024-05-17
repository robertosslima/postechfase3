package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.RestaurantDTO;
import postech.fiap.fase3.reserva.utils.RestaurantHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class RestaurantControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldRegister() {
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "Ema",
                "R. Bela Cintra, 1551 - Consolação, São Paulo - SP, 01415-001",
                "BRAZILIAN",
                RestaurantHelper.buildWorkingHourList(),
                100
        );

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurantDTO)
        .when()
                .post("/restaurant")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindById() {
        String id = "3e9bbc25-53ca-443c-bce0-11518e45f8f8";
        when()
                .get("/restaurant/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindByName() {
        String name = "Ema";
        when()
                .get("/restaurant/find-by-name/{name}", name)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindByAddressContaining() {
        String address = "R. Bela Cintra, 1551 - Consolação, São Paulo - SP, 01415-001";
        when()
                .get("/restaurant/find-by-address/{address}", address)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindByCuisineTypeContaining() {
        String cuisine = "BRAZILIAN";
        when()
                .get("/restaurant/find-by-cuisine/{cuisine}", cuisine)
        .then()
                .statusCode(HttpStatus.OK.value());
    }
}
