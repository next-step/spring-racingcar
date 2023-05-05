package racingcar.game.web;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping
    public PlayResultResponse play(@RequestBody @Valid PlayRequest playRequest) {
        return gameService.play(playRequest);
    }
}
