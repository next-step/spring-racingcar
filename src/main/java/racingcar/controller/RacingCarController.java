package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private RacingCarDao racingCarDao;

    @PostMapping(value = "/plays", consumes = "application/json")
    public ResponseEntity startGame(@RequestBody PlayInput playInput){
        List<Car> racingCars = RacingCar.startgame(playInput);
        String winners = RacingCar.getWinner();
        int id = racingCarDao.insertPlayResult(playInput.getCount(), winners);
        racingCarDao.insertPlayCarHistory(id, racingCars);
        return ResponseEntity.ok(racingCarDao.getPlayResult(id));
    }

    @GetMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayResult>> allPlayResult() {
        return ResponseEntity.ok(racingCarDao.getAllPlayResult());
    }


}