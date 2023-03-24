package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StadiumTest {

    @DisplayName("경기를 한번에 한 후 결과를 리턴해준다.")
    @Test
    void playRacing() {

        Stadium stadium = new Stadium( new CarCollection("lucas,cyan,vince"), 10);

        RacingCarResult re = stadium.playRacingCar();


    }
}
