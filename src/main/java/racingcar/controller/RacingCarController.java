package racingcar.controller;

import racingcar.dto.RacingPlaysRequest;
import racingcar.dto.RacingPlaysResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingService;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@RestController
public class RacingCarController {
    private final RacingService racingService;

    public RacingCarController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public RacingPlaysResponse plays(@RequestBody RacingPlaysRequest racingPlaysRequest){
        return racingService.playRace(racingPlaysRequest);
    }
}
