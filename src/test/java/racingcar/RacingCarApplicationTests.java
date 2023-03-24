package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.domain.Device;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarApplicationTests {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("POST")
    @Test
    void select() {
        Device device = new Device("상수,재범",2);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(device)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

    }

    @DisplayName("GET")
    @Test
    void selectGetMapping() {

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

    }

    @DisplayName("POST AND GET")
    @Test
    void selectPostGetMapping() {
        Device device = new Device("상수,재범",2);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(device)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

    }
}
