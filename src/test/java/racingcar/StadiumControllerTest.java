package racingcar;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.test.User;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StadiumControllerTest {

    @LocalServerPort
    int port;

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
        Map<String, Object> map = new HashMap<>();

        map.put("names","lucas,cyan");
        map.put("count", 50);


        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(map)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/users/1");
    }

}
