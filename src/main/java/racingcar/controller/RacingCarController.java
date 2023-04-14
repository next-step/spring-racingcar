package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.dto.RacingHistory;
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
        List<Car> racingCars = RacingCar.racingGame(racingInput);
        //List<Car> racingCars = new RacingCar().racingGame(racingInput);
        List<String> winners = new RacingCar().getWinner();

        RacingResult racingResult = new RacingResult(racingInput.getCount(), winners, racingCars);
        RacingCarDao.setRacingRecord(racingCars);
        return ResponseEntity.ok(RacingCarDao.getRacingResult(racingResult));
    }
    /* @GetMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)*/

    @GetMapping(value = "/plays", produces = "application/json")
    public ResponseEntity<List<RacingHistory>> gameHistory() {
        return ResponseEntity.ok(RacingCar.getAllResult());
    }

}