package racingcar.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.presentation.dto.GameResultDto;
import racingcar.presentation.dto.GameStartDto;
import racingcar.business.RacingService;

@RestController
public class RacingCarApi {

    private final RacingService racingService;

    public RacingCarApi(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public GameResultDto playRacingCar(@RequestBody GameStartDto gameStartDto) {
        if (gameStartDto.isNotValid()) {
            throw new IllegalArgumentException();
        }
        return racingService.game(gameStartDto);
    }
}
