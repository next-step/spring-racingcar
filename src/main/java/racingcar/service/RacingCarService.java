package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.model.RacingRequest;
import racingcar.model.RacingResponse;
import racingcar.repository.PlayResultRepository;
import racingcar.utils.RacingCarUtils;
import racingcar.view.RacingResultView;

@Service
public class RacingCarService {

    @Autowired
    private Cars cars;
    @Autowired
    private PlayResultRepository playResultRepository;

    public RacingCarService() {
    }

    public RacingCarService(Cars cars, PlayResultRepository playResultRepository) {
        this.cars = cars;
        this.playResultRepository = playResultRepository;
    }

    @Transactional
    public RacingResponse startRacing(RacingRequest racingRequest) {
        PlayResult playResult = new PlayResult();

        cars.makeCars(playResult, RacingCarUtils.stringToList(racingRequest.getNames()));
        cars.moveCars(racingRequest.getCount());
        cars.save();

        playResult.setWinners(cars.getWinnerNames());
        playResultRepository.save(playResult);
        return new RacingResponse(cars.getWinnerNames(), cars.getCars());
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

    public List<Car> makeCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void moveCarsCli(List<Car> cars, int targetDistance) {
        for (int i = 0; i < targetDistance; i++) {
            moveCars(cars);
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
