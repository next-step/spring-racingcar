package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.HistoryDao;
import racingcar.domain.History;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingInput;
import racingcar.domain.RacingResult;
import racingcar.service.RacingGameServcie;

import java.util.List;

@RestController
public class RacingCarController {

    private RacingResult RacingCar;

    @PostMapping(value = "/plays", consumes = "application/json")
    public ResponseEntity plays(@RequestBody RacingInput racingInput) {
        List<RacingCar> racingCars = RacingGameServcie.cargame(racingInput);
        String winners = RacingGameServcie.getWinner();
        RacingResult playResult = new RacingResult(winners,racingInput.getCount(),racingCars);
        HistoryDao.insertPlayResult(winners, racingInput.getCount());
        return ResponseEntity.ok().body(playResult);
    }
    @GetMapping("/plays")
    public ResponseEntity<List<History>> history() {
        List<History> result;
        result = RacingGameServcie.History();
        return ResponseEntity.ok().body(result);
    }
}
