package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.ReviewDTO;
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
class ReviewControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReview() {
        ReviewDTO reviewDTO = new ReviewDTO(
                4,
                "",
                "Isabela Garcia",
                "3e9bbc25-53ca-443c-bce0-11518e45f8f8"
        );

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reviewDTO)
        .when()
                .post("/review")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindReviewsByRestaurantId() {
        String id = "3e9bbc25-53ca-443c-bce0-11518e45f8f8";
        when()
                .get("/review/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value());
    }
}
