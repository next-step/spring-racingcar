package racingcar.presentation;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.fixture.AssuredTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameControllerTest extends AssuredTest {


    @DisplayName("")
    @Test
    void plays() {
        // when
        ExtractableResponse<Response> response = play_요청("a,b,c", 3);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static ExtractableResponse<Response> play_요청(String name, int count) {
        Map<String, String> params = new HashMap<>();
        params.put("names", name);
        params.put("count", String.valueOf(count));

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/plays")
                .then().log().all()
                .extract();
    }

}
