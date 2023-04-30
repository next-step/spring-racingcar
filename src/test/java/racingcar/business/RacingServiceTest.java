package racingcar.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.RacingCar;
import racingcar.data.RacingRepository;
import racingcar.presentation.dto.GameStartDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RacingServiceTest {

    private final RacingService racingService;

    public RacingServiceTest() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RacingRepository racingRepository = new RacingRepository(jdbcTemplate);
        this.racingService = new RacingService(racingRepository);
    }

    @DisplayName("자동차 이름 입력값으로 반환된 자동차 갯수를 확인한다.")
    @Test
    void getRacingCarsTest() {
        // given
        String names = "A,B,C,D,E";
        GameStartDto gameStartDto = new GameStartDto(names, 0);

        // when
        List<RacingCar> racingCars = racingService.getRacingCars(gameStartDto);

        // then
        int playerCount = names.split(",").length;
        assertThat(racingCars.size()).isEqualTo(playerCount);
    }

    @DisplayName("시도할 횟수 입력값과 실제 플레이된 게임 횟수를 비교한다.")
    @Test
    void startCarRacingGameCountTest() {
        // given
        int inputCount = 10;
        List<RacingCar> racingCars = IntStream.rangeClosed(1, 10).mapToObj(i -> new RacingCar(String.valueOf(i)))
                .collect(Collectors.toList());

        // when
        int gameCount = racingService.startCarRacingGame(racingCars, inputCount);

        // then
        assertThat(inputCount).isEqualTo(gameCount);
    }

}