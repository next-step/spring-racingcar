package racingcar.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameStartDto;
import racingcar.service.RacingService;

@RestController
public class RacingCarApi {

    private final RacingService racingService;

    public RacingCarApi(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public GameResultDto playRacingCar(@RequestBody GameStartDto gameStartDto) {
        return racingService.game(gameStartDto);
    }
}
