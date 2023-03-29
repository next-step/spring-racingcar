package racingcar;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import racingcar.domain.RacingCarRequest;

public class RacingCarApplicationTests {

    @Test
    void contextLoads() {
        RestAssured
                .given().log().all()
                .body(new RacingCarRequest("sean, rose, jay", 8))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .post("/plays")
                .then()
                .log().all()
                .extract();
    }
}
