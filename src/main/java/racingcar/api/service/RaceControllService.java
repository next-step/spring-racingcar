package racingcar.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import racingcar.api.response.CarRacingResponse;
import racingcar.api.response.Car;
import racingcar.database.RaceResult;
import racingcar.database.RacingCarDao;
import racingcar.racing.Driver;
import racingcar.racing.RaceControll;
import racingcar.racing.RacingCar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceControllService {

    private int raceRound = 0; // 플레이라운드
    
    public CarRacingResponse race(String drivers, int round) {

        RaceControll raceControll = new RaceControll(drivers);
        raceControll.raceMultiRound(round);

        String winners = raceControll.getWinners()
                .stream().map(Driver::getName)
                .collect(Collectors.joining(", "));

        List<RacingCar> finishingCars = raceControll.getCars();
        Collections.sort(finishingCars);
        List<Car> cars = new ArrayList<>();
        finishingCars.stream()
                .forEach(
                (RacingCar racingCar) -> {
                    cars.add(new Car(racingCar));
                }
        );

        this.raceRound += 1;
        this.commitResult(round, raceControll.getWinners(), finishingCars);

        return new CarRacingResponse(winners, cars);
    }

    /**
     * DB에 실행결과 저장
     *
     * @param round      실행 횟수
     * @param winners    우승자
     * @param racingCars 레이스카
     * @return
     */
    private ResponseEntity<RaceResult> commitResult(int round, List<Driver> winners, List<RacingCar> racingCars) {
        RaceResult raceResult = new RaceResult(round, winners, racingCars);

        for(RacingCar car : raceResult.getRacingCars()) {
            RacingCarDao.setRacingHistory(car, raceRound);
        }

        return ResponseEntity.ok(RacingCarDao.setRacingResult(raceResult, raceRound));
    }
}
