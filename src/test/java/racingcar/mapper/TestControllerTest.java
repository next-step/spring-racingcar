package racingcar.mapper;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.test.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTest {

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
    @DisplayName("Media Type - Content-Type")
    @Test
    void createUser() {
        User user = new User("이름", "email@email.com");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .when().post("/media-type/users")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/users/1");
    }

}
