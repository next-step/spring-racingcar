package racingcar.game.web;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.game.dto.PlayRequest;
import racingcar.game.application.GameService;
import racingcar.game.dto.PlayResultResponse;

@RequestMapping("/plays")
@RestController
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public List<PlayResultResponse> loadGameHistory() {
        return gameService.loadGameHistory();
    }

    @PostMapping
    public PlayResultResponse play(@RequestBody @Valid PlayRequest playRequest) {
        return gameService.play(playRequest);
    }
}
