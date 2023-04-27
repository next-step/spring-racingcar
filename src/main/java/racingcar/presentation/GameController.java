package racingcar.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.application.GameService;
import racingcar.presentation.dto.PlayRequest;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/plays")
    public ResponseEntity<Void> plays(@RequestBody PlayRequest request) {

        gameService.play(request);

        return ResponseEntity.ok()
                .build();
    }

}
