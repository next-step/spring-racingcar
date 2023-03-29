package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.CarList;

import java.util.ArrayList;
import java.util.List;

public class CarNameRequest {

    private final CarList carList;

    public CarNameRequest(CarList carList) {
        this.carList = carList;
    }

    public static CarNameRequest carListRequest(String carNamesParam) {
        List<Car> cars = new ArrayList<>();
        String carNames = carNamesParam;

        for (String carName : carNames.split(",")) {
            cars.add(new Car(carName));
        }

        return new CarNameRequest(new CarList(cars));
    }

    public CarList getCarNameList() {
        return carList;
    }


}
