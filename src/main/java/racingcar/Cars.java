package racingcar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private List<Car> cars = new ArrayList<>();

    public Cars(List<String> carEntry) {
        for (int i = 0; i < carEntry.size(); i++) {
            cars.add(new Car(carEntry.get(i)));
        }
    }

    public void getCarMove(RandomMove randomMove) {
        for (int i = 0; i < this.cars.size(); i++) {
            this.cars.get(i).move(randomMove.movable());
        }
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public List<Car> getFastCars() {
        Collections.sort(this.cars);
        return this.cars.stream()
                .filter(p -> p.getPosition() == this.cars.get(0).getPosition())
                .collect(Collectors.toList());
    }
}
