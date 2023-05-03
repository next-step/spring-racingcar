package racingcar.api.controller;

import org.springframework.web.bind.annotation.*;
import racingcar.api.dto.PlayRequest;
import racingcar.api.dto.PlayResultResponse;
import racingcar.domain.entity.PlayResult;
import racingcar.domain.entity.RacingCars;
import racingcar.domain.service.RacingCarGameService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plays")
public class RacingCarController {

    private RacingCarGameService racingCarGameService;
    public RacingCarController(RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }

    @GetMapping
    public List<PlayResultResponse> getPlayResults() {
        List<PlayResult> playResults = racingCarGameService.getPlayResults();
        return playResults.stream()
                        .map(PlayResultResponse::of)
                        .collect(Collectors.toList());
    }

    @PostMapping
    public PlayResultResponse play(@RequestBody @Valid PlayRequest request) {
        PlayResult playResult = racingCarGameService.playGame(RacingCars.from(request.getNames()), request.getCount());
        return PlayResultResponse.of(playResult);
    }
}
