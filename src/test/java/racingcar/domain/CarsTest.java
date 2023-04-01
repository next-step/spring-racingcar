package racingcar.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CarsTest {
    @Test
    public void makeCarsTest() {
        Cars cars = Cars.makeCars("kia, volvo, bmw");
        assertThat(cars.getCars().size(), is(3));
    }

    @Test
    public void getWinnerNamesTest() {
        Cars cars = Cars.makeCars("kia, volvo");
        cars.getCars().get(0).move(true);
        cars.getCars().get(0).move(true);
        cars.getCars().get(0).move(true);
        cars.getCars().get(1).move(true);
        assertThat(cars.getWinnerNames(), is("kia"));
    }

    @Test
    public void getMaxDistanceTest() {
        Cars cars = Cars.makeCars("kia, volvo");
        cars.getCars().get(0).move(true);
        cars.getCars().get(0).move(true);
        cars.getCars().get(0).move(true);
        cars.getCars().get(1).move(true);
        assertThat(cars.getMaxDistance(), is(3));
    }

    @Test
    public void getWinnerCarsTest() {
        Cars cars = Cars.makeCars("kia, volvo, bmw");
        cars.getCars().get(0).move(true);
        cars.getCars().get(0).move(true);
        cars.getCars().get(0).move(true);
        cars.getCars().get(1).move(true);
        cars.getCars().get(1).move(true);
        cars.getCars().get(1).move(true);
        assertThat(cars.getWinnerCars().size(), is(2));
    }
}
