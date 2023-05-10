package racingcar.presentation;

import org.springframework.web.bind.annotation.*;
import racingcar.business.RacingService;
import racingcar.presentation.dto.GamePlayHistory;
import racingcar.presentation.dto.GameResultDto;
import racingcar.presentation.dto.GameStartDto;

import java.util.List;

@RequestMapping("/plays")
@RestController
public class RacingCarApi {

    private final RacingService racingService;

    public RacingCarApi(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping
    public GameResultDto playRacingCar(@RequestBody GameStartDto gameStartDto) {
        if (gameStartDto.isNotValid()) {
            throw new IllegalArgumentException();
        }
        return racingService.game(gameStartDto.getNames(), gameStartDto.getCount());
    }

    @GetMapping
    public List<GamePlayHistory> getGamesResults() {
        return racingService.getGameResults();
    }
}
