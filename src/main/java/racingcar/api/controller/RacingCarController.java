package racingcar.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.api.dto.RacingCarRequest;
import racingcar.api.dto.RacingCarResponse;
import racingcar.domain.entity.RacingCars;
import racingcar.domain.service.RacingCarService;

import java.util.List;

@RestController
public class RacingCarController {

    private RacingCarService racingCarService;
    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public RacingCarResponse play(@RequestBody RacingCarRequest request) {
        RacingCars racingCars = racingCarService.playRounds(RacingCars.from(request.getNames()), request.getCount());
        List<String> winners = racingCars.getWinners();
        return new RacingCarResponse(winners, racingCars.getRacingCars());
    }
}
