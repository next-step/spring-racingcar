package racingcar.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.api.dto.RacingCarRequest;
import racingcar.api.dto.RacingCarResponse;
import racingcar.domain.entity.PlayResult;
import racingcar.domain.entity.RacingCars;
import racingcar.domain.service.RacingCarGameService;

import javax.validation.Valid;

@RestController
public class RacingCarController {

    private RacingCarGameService racingCarGameService;
    public RacingCarController(RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }

    @PostMapping("/plays")
    public RacingCarResponse play(@RequestBody @Valid RacingCarRequest request) {
        PlayResult playResult = racingCarGameService.playGame(RacingCars.from(request.getNames()), request.getCount());
        return RacingCarResponse.of(playResult);
    }
}
