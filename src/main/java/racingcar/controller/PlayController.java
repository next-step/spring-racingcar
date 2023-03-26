package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.service.RacingService;
import racingcar.repository.InsertDao;

import java.util.List;

@RestController
public class PlayController {

    @PostMapping(value = "/plays", consumes = "application/json")
    public ResponseEntity startGame(@RequestBody PlayInput playInput){
        List<RacingCar> racingCars = RacingService.playgame(playInput);
        List<String> winners = RacingService.getWinner();
        PlayResult playResult = new PlayResult(playInput.getCount(), winners, racingCars);
        return ResponseEntity.ok(InsertDao.insertWithMap(playResult));
    }
}
