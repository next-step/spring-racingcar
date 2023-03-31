package racingcar;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.RacingInput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RacingCarApplicationTests {
    @LocalServerPort
    int port;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
//        jdbcTemplate.execute("DROP TABLE RACING_CAR IF EXISTS");
////        jdbcTemplate.execute("CREATE TABLE RACING_CAR(" +
////                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
//        jdbcTemplate.execute("CREATE TABLE RACING_CAR (\n" +
//                "                            ID          INT         NOT NULL AUTO_INCREMENT,\n" +
//                "                            WINNERS     VARCHAR(50) NOT NULL,\n" +
//                "                            TRIALCOUNT  INT         NOT NULL,\n" +
//                "                            CREATED_AT  DATETIME    NOT NULL default current_timestamp,\n" +
//                "                            PRIMARY KEY (ID) )");

    }

    @DisplayName("POST")
    @Test
    void select() {
        RacingInput racing = new RacingInput("테스트1,테스트2", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racing)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());;

    }

    @DisplayName("GET")
    @Test
    void selectGetMapping() {

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());;

    }

//    @Test
//    void name() {
//        String sql = "select count(*) from RACING_CAR";
//        jdbcTemplate.queryForObject(sql, Integer.class);
//    }
}
