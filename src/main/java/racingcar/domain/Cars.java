package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import racingcar.repository.CarRepository;
import racingcar.utils.RacingCarUtils;
import racingcar.view.RacingResultView;

@Service
@RequiredArgsConstructor
public class Cars {
    private final CarRepository carRepository;

    private List<Car> cars;

    public List<Car> getCars() {
        return this.cars;
    }

    public void makeCars(PlayResult playResult, String carNames) {
        this.cars = RacingCarUtils.stringToList(carNames).stream()
                .map(name -> new Car(playResult, name))
                .collect(Collectors.toList());
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
        int maxDistance = getMaxDistance(cars);
        List<String> winners = getWinnerCars(cars, maxDistance);

        return String.join(", ", winners);
    }

    private int getMaxDistance(List<Car> cars) {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .get();
    }

    private List<String> getWinnerCars(List<Car> cars, int maxDistance) {
        return cars.stream()
                .filter(car -> car.getPosition() == maxDistance)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public void printResult() {
        RacingResultView.printResult(getWinnerNames(), cars);
    }
}
