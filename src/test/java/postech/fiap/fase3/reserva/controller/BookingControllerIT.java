package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.BookingDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class BookingControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldBookATable() {
        BookingDTO bookingDTO = new BookingDTO(
                2,
                "Lucas",
                LocalDateTime.parse("2025-05-08T19:00:00"),
                "3e9bbc25-53ca-443c-bce0-11518e45f8f8"
        );
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(bookingDTO)
        .when()
                .post("/booking")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindByRestaurantId() {
        String id = "3e9bbc25-53ca-443c-bce0-11518e45f8f8";
        when()
                .get("/booking/find-by-restaurant-id/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindByClientName() {
        String clientName = "Lucas Pereira";
        when()
                .get("/booking/find-by-client-name/{clientName}", clientName)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
