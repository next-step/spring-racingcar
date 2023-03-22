package racingcar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.Service.RacingService;
import racingcar.model.PlayResultOut;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RacingCarApplicationTests {

    @Autowired
    RacingService svc;

    @Test
    void contextLoads() {
    }

    @ParameterizedTest
    @CsvSource(value = {"bill,max,soap=10", "ann,ted,emma=20"}, delimiter = '=')
    void racingTest(String name, String count) {
        PlayResultOut pro = svc.racing(name, Integer.parseInt(count));
        assertThat(pro).isNotNull();
    }

}
