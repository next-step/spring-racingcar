package racingcar.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.application.GameService;
import racingcar.application.dto.GameRequest;
import racingcar.application.dto.GameResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plays")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameResponse> game(@Valid @RequestBody GameRequest gameRequest) {
        return ResponseEntity.ok(gameService.saveGameResult(gameRequest));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> result() {
        return ResponseEntity.ok(gameService.findResult());
    }

}
