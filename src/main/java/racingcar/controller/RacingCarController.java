package racingcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingStartDto;
import racingcar.dto.RacingResultResponse;
import racingcar.dto.RacingStartRequest;
import racingcar.service.RacingCarService;

@RestController
@RequiredArgsConstructor
public class RacingCarController {
    private final RacingCarService racingCarService;
    @PostMapping("/plays")
    public RacingResultResponse plays(@RequestBody RacingStartRequest request) {
        return racingCarService.playRacingGame(
                new RacingStartDto(request.getNames(), request.getCount())
        );
    }
}
