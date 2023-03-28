package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingStartDto;
import racingcar.reponse.RacingResultResponse;
import racingcar.request.RacingStartRequest;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {

    @PostMapping("/plays")
    public RacingResultResponse plays(@RequestBody RacingStartRequest request) {
        return RacingCarService.playRacingGame(
                new RacingStartDto(request.getNames(), request.getCount())
        );
    }
}
