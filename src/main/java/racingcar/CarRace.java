package racingcar;

public class CarRace {
    public Winners getWinners(Cars cars) {
        return new Winners(cars.getFastCars());
    }

    RandomMove randomMove = new RandomMove();

    public void racing(Cars cars) {
        cars.getCarMove(randomMove);
    }
}