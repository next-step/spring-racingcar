package racingcar.game.web;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.game.application.GameService;
import racingcar.game.web.dto.PlayRequest;
import racingcar.game.web.dto.PlayResultResponse;

@RequestMapping("/plays")
@RestController
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<PlayResultResponse>> loadGameHistory() {
        return ResponseEntity.ok(PlayResultResponse.from(gameService.loadGameHistory()));
    }

    @PostMapping
    public ResponseEntity<PlayResultResponse> play(@RequestBody @Valid PlayRequest playRequest) {
        return ResponseEntity.ok(PlayResultResponse.from(gameService.play(playRequest.toGameStartData())));
    }
}
