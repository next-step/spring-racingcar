package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.ToString;
import racingcar.repository.CarRepository;
import racingcar.utils.RacingCarUtils;
import racingcar.view.RacingResultView;

@Service
public class Cars {
    private final CarRepository carRepository;

    @ToString.Exclude
    private List<Car> cars;

    public Cars(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public void makeCars(PlayResult playResult, String carNames) {
        this.cars = RacingCarUtils.stringToList(carNames).stream()
                .map(name -> new Car(playResult, name))
                .collect(Collectors.toList());
    }

    public void makeCars(String carNames) {
        PlayResult playResult = new PlayResult();
        makeCars(playResult, carNames);
    }

    public void moveCars(int targetDistance) {
        IntStream.range(0, targetDistance)
                .forEach(it -> {
                    moveCars(cars);
                });
    }

    private void moveCars(List<Car> cars) {
        cars.stream()
                .forEach(Car::move);
    }

    @Transactional
    public void save() {
        cars.forEach(car -> carRepository.save(car));
    }

    public String getWinnerNames() {
        List<String> winners = getWinnerCars();
        return String.join(", ", winners);
    }

    public int getMaxDistance() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .get();
    }

    public List<String> getWinnerCars() {
        return cars.stream()
                .filter(car -> car.getPosition() == getMaxDistance())
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public void printResult() {
        RacingResultView.printResult(getWinnerNames(), cars);
    }

    @Override
    public String toString() {
        return cars.stream().map(car -> car.getName()).toList().toString();
    }

}
