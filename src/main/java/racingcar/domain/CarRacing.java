package racingcar.domain;

public class CarRacing {

    private final CarList carList;
    private final int playNumber;

    public CarRacing(CarList carList, int playNumber) {
        this.carList = carList;
        this.playNumber = playNumber;
    }

    public void playAll() {
        for (int i = 0; i < playNumber; i++) {
            playAllCars();
        }
    }

    private void playAllCars() {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < carList.getCars().size(); i++) {
            carList.getCars().get(i).move(numberGenerator);
        }
    }

    public CarList getCarList() {
        return carList;
    }
}
