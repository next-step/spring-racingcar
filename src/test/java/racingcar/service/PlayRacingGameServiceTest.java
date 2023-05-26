package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import racingcar.ServiceTest;
import racingcar.service.request.PlayRacingGameRequest;

import javax.validation.ConstraintViolation;
import javax.xml.validation.ValidatorHandler;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayRacingGameServiceTest extends PlayRacingGameMockTest {

    @InjectMocks private PlayRacingGameService playRacingGameService;

    @Nested
    @DisplayName("게임을 시작할 때 요청 값이")
    class request {

        @DisplayName("이름 리스트가 null이면 에러가 난다.")
        @Test
        void nameListNull() {
            //given
            List<String> nameList = null;
            int totalCount = 1;
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, totalCount);
            //when
            List<ServiceTest.ParameterValid> validate = validate(request);

            //then
            assertThat(validate.size()).isEqualTo(1);
            assertThat(validate).extracting("path").containsExactly("nameList");
        }

        @DisplayName("이름 리스트가 비어 있으면 에러가 난다.")
        @Test
        void nameListEmpty() {
            //given
            List<String> nameList = List.of();
            int totalCount = 1;
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, totalCount);
            //when
            List<ServiceTest.ParameterValid> validate = validate(request);

            //then
            assertThat(validate.size()).isEqualTo(1);
            assertThat(validate).extracting("path").containsExactly("nameList");
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        @DisplayName("total count가 0이하면 에러가 난다.")
        void totalCount(int trialCount) {
            //given
            List<String> nameList = List.of("드록바");
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, trialCount);
            //when
            List<ServiceTest.ParameterValid> validate = validate(request);

            //then
            assertThat(validate.size()).isEqualTo(1);
            assertThat(validate).extracting("path").containsExactly("trialCount");
        }
    }


}
