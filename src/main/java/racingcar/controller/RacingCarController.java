package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.dto.PlayInput;
import racingcar.dto.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.dao.RacingCarDao;

import java.util.List;

@RestController
public class RacingCarController {

    @PostMapping(value = "/plays", consumes = "application/json")
    public ResponseEntity startGame(@RequestBody PlayInput playInput){
        List<Car> racingCars = RacingCar.startgame(playInput);
        List<String> winners = RacingCar.getWinner();
        PlayResult playResult = new PlayResult(playInput.getCount(), winners, racingCars);
        RacingCarDao.setPlayHistory(racingCars);
        return ResponseEntity.ok(RacingCarDao.getPlayResult(playResult));
    }
}