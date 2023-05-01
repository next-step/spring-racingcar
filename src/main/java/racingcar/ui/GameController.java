package racingcar.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.application.GameService;
import racingcar.application.dto.GameRequest;
import racingcar.application.dto.GameResponse;
import racingcar.domain.RacingCars;
import racingcar.domain.Winners;
import racingcar.global.NameEmptyException;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> game(@RequestBody GameRequest gameRequest) {
        if(gameRequest.isNameEmpty()) {
            throw new NameEmptyException();
        }

        RacingCars racingCars = RacingCars.from(gameRequest.getNames());
        racingCars.move(gameRequest.getCount());
        Winners winners = new Winners(racingCars.getWinner());

        gameService.saveGameResult(gameRequest.getCount(), racingCars, winners);
        return ResponseEntity.ok(GameResponse.of(winners, racingCars));
    }

}
