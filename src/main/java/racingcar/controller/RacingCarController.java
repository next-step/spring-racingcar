package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.dto.RacingInput;
import racingcar.dto.RacingResult;
import racingcar.domain.RacingCar;
import racingcar.dao.RacingCarDao;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RacingCarController {

    @PostMapping(value = "/plays", consumes = "application/json")
    public ResponseEntity<RacingResult> startGame(@RequestBody RacingInput racingInput){
        //List<Car> racingCars = RacingCar.racingGame(racingInput);
        List<Car> racingCars = new RacingCar().racingGame(racingInput);
        List<String> winners = new RacingCar().getWinner();

        RacingResult racingResult = new RacingResult(racingInput.getCount(), winners, racingCars);
        RacingCarDao.setRacingRecord(racingCars);
        return ResponseEntity.ok(RacingCarDao.getRacingResult(racingResult));
    }
}