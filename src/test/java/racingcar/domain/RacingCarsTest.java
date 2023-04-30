package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RacingCarsTest {

    @Test
    void RacingCars가_List로_생성됩니다(){
        // given
        RacingCar wonnie = new RacingCar("워니");
        RacingCar jason = new RacingCar("제이슨");
        RacingCar brown = new RacingCar("브라운");

        List<RacingCar> list = new ArrayList<>();
        list.add(wonnie);
        list.add(jason);
        list.add(brown);

        // when
        RacingCars racingCars = new RacingCars(list);

        // then
        assertThat(racingCars.size()).isEqualTo(3);
        assertThat(racingCars.get(0)).isEqualTo(wonnie);
        assertThat(racingCars.get(1)).isEqualTo(jason);
        assertThat(racingCars.get(2)).isEqualTo(brown);
    }

    @Test
    void RacingCars가_names으로_생성됩니다(){
        // given
        String names = "워니,제이슨,브라운";

        // when
        RacingCars racingCars = new RacingCars(names);

        // then
        RacingCar wonnie = new RacingCar("워니");
        RacingCar jason = new RacingCar("제이슨");
        RacingCar brown = new RacingCar("브라운");

        assertThat(racingCars.size()).isEqualTo(3);
        assertThat(racingCars.get(0)).isEqualTo(wonnie);
        assertThat(racingCars.get(1)).isEqualTo(jason);
        assertThat(racingCars.get(2)).isEqualTo(brown);
    }
}