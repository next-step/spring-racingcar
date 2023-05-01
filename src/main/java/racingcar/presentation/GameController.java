package racingcar.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.application.GameService;
import racingcar.presentation.dto.PlayHistoryResponse;
import racingcar.presentation.dto.PlayRequest;
import racingcar.presentation.dto.PlayResponse;

@RestController
@RequestMapping("/plays")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<PlayResponse> plays(@RequestBody PlayRequest request) {
        PlayResponse response = gameService.play(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PlayHistoryResponse> getPlays() {
        PlayHistoryResponse response = gameService.findPlays();

        return ResponseEntity.ok(response);
    }

}
