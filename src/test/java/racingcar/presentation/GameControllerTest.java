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

    @DisplayName("게임 실행 요청, 응답 테스트")
    @Test
    void plays() {
        // when
        int gameCount = 3;
        ExtractableResponse<Response> response = play_요청("a,b,c", gameCount);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("winners")).isNotNull();
        assertThat(response.jsonPath().getList("racingCars").size()).isEqualTo(gameCount);
    }

    @DisplayName("게임 이력 조회 요청, 응답 테스트")
    @Test
    void getPlays() {
        // given
        int gameCount = 3;
        int expect = 2;
        String players = "a,b,c";
        play_요청(players, gameCount);
        play_요청(players, gameCount);

        // when
        ExtractableResponse<Response> response = play_history_조회_요청();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("$")).hasSize(expect);
    }

    private static ExtractableResponse<Response> play_요청(String name, int count) {
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

    private static ExtractableResponse<Response> play_history_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .extract();
    }

}
