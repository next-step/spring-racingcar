package racingcar;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.RacingStartDto;

@SpringBootTest
class RacingCarApplicationTests {

	@Test
	void contextLoads() {
		RestAssured
				.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(
						new RacingStartDto("워니,제이슨.브라운", 50)
				)
				.when().post("/plays")
				.then().log().all()
				.statusCode(HttpStatus.OK.value())
				.extract();
	}

}


//POST /plays HTTP/1.1
//		content-type: application/json; charset=UTF-8
//		host: localhost:8080
//
//		{
//		"names": "워니,제이슨,브라운",
//		"count": 50
//		}