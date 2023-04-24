package racingCar;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingCar.model.PlayHistoryResponse;
import racingCar.model.RacingGameRequest;
import racingCar.model.RacingGameResponse;

@RestController
public class RacingCarController {

    RacingCarService racingGameService;

    public RacingCarController(RacingCarService racingGameService) {
        this.racingGameService = racingGameService;
    }
    @PostMapping("/plays")
    public RacingGameResponse plays(@RequestBody RacingGameRequest inputData) {

        return racingGameService.play(inputData);
    }
    @GetMapping("/plays")
    public List<PlayHistoryResponse> getPlayHistory() {
        return racingGameService.getPlayHistory();
    }
}
