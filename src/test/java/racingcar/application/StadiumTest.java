package racingcar.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.application.Stadium;
import racingcar.domain.CarCollection;
import racingcar.dto.RacingCarResultDto;


import static org.assertj.core.api.Assertions.assertThat;

public class StadiumTest {

    @DisplayName("경기를 한번에 한 후 결과를 리턴해준다.")
    @Test
    void playRacing() {

        Stadium stadium = new Stadium(new CarCollection("lucas,cyan,vince"), 10, () -> 1);

        RacingCarResultDto re = stadium.playRacingCar();


        assertThat(re.getWinners())
                .isEqualTo("lucas,cyan,vince");

        assertThat(re.getRacingCars())
                .hasSize(3);
    }
}
