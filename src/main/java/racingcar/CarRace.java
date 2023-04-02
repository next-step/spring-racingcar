package racingcar;

public class CarRace {
    RandomMove randomMove = new RandomMove();

    public Winners getWinners(Cars cars) {
        return new Winners(cars.getFastCars());
    }

    public void racing(Cars cars) {
        cars.getCarMove(randomMove);
    }
}
