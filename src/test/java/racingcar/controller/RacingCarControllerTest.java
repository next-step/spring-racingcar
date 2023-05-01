package racingcar.controller;

import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import racingcar.dto.PlaysRequestDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RacingCarControllerTest {
	private final String NAMES = "aaa,bbb,ccc";
	private final Integer COUNT = 10;

	@LocalServerPort
	int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@DisplayName("play 수행시 우승자와 결과를 응답받는다.")
	@Test
	void postPlays() {
		Map<String, Object> request = new HashMap<>();
		request.put("names", NAMES);
		request.put("count", COUNT);

		RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when().post("/plays")
			.then().log().all()
			.statusCode(HttpStatus.OK.value())
			.body("winners", notNullValue())
			.body("racingCars", notNullValue());
	}
}
