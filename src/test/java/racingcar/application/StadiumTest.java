package racingcar.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.application.Stadium;

import racingcar.domain.Car;

import racingcar.domain.CarCollection;
import racingcar.dto.RacingCarResultDto;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class StadiumTest {

    @DisplayName("자동차들 중에서 가장 멀리 간 위치가 어디인지 알 수 있다.")
    @Test
    void getMaxCarPosition() {

        List<Car> cars = new ArrayList<>();

        int maxPosition = 5;

        cars.add(new Car("lucas", maxPosition));
        cars.add(new Car("kai"));

        Stadium stadium = new Stadium(new CarCollection(cars), 2, () -> { return 1;});

        assertEquals(stadium.getMaxCarPosition(), maxPosition);
    }

    @Test
    @DisplayName("특정위치의 차 위치")
    void specificLocationCar() {
        List<Car> cars = new ArrayList<>();

        int defaultPosition = 0;
        int testPosition = 5;

        cars.add(new Car("lucas", testPosition));
        cars.add(new Car("cas",  testPosition));
        cars.add(new Car("kai", defaultPosition));

        Stadium stadium = new Stadium(new CarCollection(cars), 2, () -> { return 1;});

        List<Car> actaul1 = stadium.getSpecificLocationCars(0);
        List<Car> actaul2 = stadium.getSpecificLocationCars(testPosition);

        assertEquals(actaul1.size(), 1);
        assertEquals(actaul2.size(), 2);
    }

    @DisplayName("경기가 종료되지 않았다면 round가 증가되면서 차들이 경주를 한다.")
    @Test
    public void racingCars() {

        List<Car> cars = new ArrayList<>();

        int defaultPosition = 0;
        int winnerPosition = 5;

        cars.add(new Car("lucas", winnerPosition));
        cars.add(new Car("cas", winnerPosition));
        cars.add(new Car("kai", defaultPosition));

        Stadium stadium = new Stadium(new CarCollection(cars), 5, () -> { return 1;} );


        List<Car> actualCars = stadium.racingCars();
        int actualRound = stadium.getRound();

        assertThat(actualCars)
                .hasSize(3)
                .extracting(Car::getName)
                .containsExactlyInAnyOrder("lucas", "cas", "kai");

        assertThat(actualRound).isEqualTo(1);
    }

    @DisplayName("경기가 종료되었는데 경주를 한다면 예외가 발생한다.")
    @Test
    public void ifRacingOverTotalRound() {

        List<Car> cars = new ArrayList<>();

        int defaultPosition = 0;
        int winnerPosition = 5;

        cars.add(new Car("lucas", winnerPosition));
        cars.add(new Car("cas", winnerPosition));
        cars.add(new Car("kai", defaultPosition));

        Stadium stadium = new Stadium(new CarCollection(cars), 1, () -> { return 1;} );

        assertThatExceptionOfType(IllegalCallerException.class)
                .isThrownBy(() -> {
                            stadium.racingCars();
                            stadium.racingCars();
                        }
                ).withMessageContaining("경기는 종료");

    }

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
