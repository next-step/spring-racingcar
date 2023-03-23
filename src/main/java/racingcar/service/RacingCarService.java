package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import racingcar.domain.Car;
import racingcar.domain.PlayResult;
import racingcar.model.RacingRequest;
import racingcar.model.RacingResponse;
import racingcar.repository.CarRepository;
import racingcar.repository.PlayResultRepository;
import racingcar.utils.RacingCarUtils;
import racingcar.view.RacingResultView;

@Service
@RequiredArgsConstructor
public class RacingCarService {

    private final CarRepository carRepository;
    private final PlayResultRepository playResultRepository;

    @Transactional
    public RacingResponse startRacing(RacingRequest racingRequest) {

        List<Car> cars = makeCars(RacingCarUtils.stringToList(racingRequest.getNames()));
        moveCars(cars, racingRequest.getCount());
        cars.forEach(car -> carRepository.save(car));

        playResultRepository.save(PlayResult.builder()
                .winners(getWinnerNames(cars))
                .build());
        return new RacingResponse(getWinnerNames(cars), cars);
    }

    public void startRacing(List<String> carNames, int targetDistance) {
        // make cars
        List<Car> cars = makeCars(carNames);

        // move cars
        RacingResultView.printStartRacing();
        moveCars(cars, targetDistance);

        // print winner
        RacingResultView.printResult(getWinnerNames(cars));
    }

    public String getWinnerNames(List<Car> cars) {
        int maxDistance = getMaxDistance(cars);
        List<String> winners = getWinnerCars(cars, maxDistance);

        return String.join(", ", winners);
    }

    public int getMaxDistance(List<Car> cars) {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .get();
    }

    public List<String> getWinnerCars(List<Car> cars, int maxDistance) {
        return cars.stream()
                .filter(car -> car.getPosition() == maxDistance)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public void printCars(List<Car> cars) {
        cars.stream()
                .forEach(car -> RacingResultView.printNameAndDistance(car.getName(), car.getPosition()));
    }

    public List<Car> makeCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void moveCarsCli(List<Car> cars, int targetDistance) {
        for (int i = 0; i < targetDistance; i++) {
            moveCars(cars);
            printCars(cars);
            RacingResultView.printNewLine();
        }
    }

    public void moveCars(List<Car> cars, int targetDistance) {
        IntStream.range(0, targetDistance)
                .forEach(it -> {
                    moveCars(cars);
                });
    }

    public void moveCars(List<Car> cars) {
        cars.stream()
                .forEach(Car::move);
    }

}
