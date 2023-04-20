package racingcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingResultResponse;
import racingcar.dto.RacingStartDto;
import racingcar.dto.RacingStartRequest;
import racingcar.service.RacingCarWebService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RacingCarController {
    private final RacingCarWebService racingCarWebService;

    @GetMapping("/plays")
    public List<RacingResultResponse> get_plays() {
        return racingCarWebService.getRacingGame();
    }

    @PostMapping("/plays")
    public RacingResultResponse plays(@RequestBody RacingStartRequest request) {
        return racingCarWebService.playRacingWebGame(
                new RacingStartDto(request.getNames(), request.getCount())
        );
    }
}
