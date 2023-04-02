package racingcar;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.application.StadiumService;
import racingcar.dto.PlaysDto;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StadiumControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private StadiumService stadiumService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * MediaTypeController > createUser 메서드
     * > produces 값으로 APPLICATION_JSON_VALUE 받기
     */
    @DisplayName("plays test")
    @Test
    void playsTest() {

        PlaysDto playsDto = new PlaysDto("lucas,cyan", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(playsDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("racingCars.name", hasItems("lucas","cyan"));
    }

    @DisplayName("plays test")
    @Test
    void playsHistoryTest() {

        stadiumService.playRacingResult(new PlaysDto("lucas,cyan", 5));
        stadiumService.playRacingResult(new PlaysDto("lucas,kai", 5));

        RestAssured.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("[0].racingCars.name", hasItems("lucas","cyan"));
    }

}
