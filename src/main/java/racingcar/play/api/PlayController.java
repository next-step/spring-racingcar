package racingcar.play.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.play.api.dto.PlayRequest;
import racingcar.play.application.PlayService;
import racingcar.play.application.dto.PlayResultResponse;

@RequestMapping("/plays")
@RestController
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @PostMapping
    public PlayResultResponse play(@RequestBody PlayRequest playRequest) {
        return playService.playGame(playRequest);
    }
}
