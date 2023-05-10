package racingcar.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.application.GameService;
import racingcar.application.dto.GameRequest;
import racingcar.application.dto.GameResponse;

import javax.validation.Valid;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> game(@Valid @RequestBody GameRequest gameRequest) {
        return ResponseEntity.ok(gameService.saveGameResult(gameRequest));
    }

}
