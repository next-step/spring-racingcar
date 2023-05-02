package racingcar.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import racingcar.domain.dto.RacingGameResultDto;
import racingcar.domain.service.RacingCarService;
import racingcar.web.dto.PlaysRequest;
import racingcar.web.dto.PlaysResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameController {

    private final RacingCarService racingCarService;

    @GetMapping("/plays")
    public List<PlaysResponse> getHistory() {
        return racingCarService.getHistory().stream()
                .map(PlaysResponse::from)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/plays",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PlaysResponse plays(@Valid @RequestBody PlaysRequest playsRequest) {
        log.info(playsRequest.toString());
        RacingGameResultDto gameResult = racingCarService.plays(playsRequest.getNames(),
                playsRequest.getCount());

        return PlaysResponse.from(gameResult);
    }
}
