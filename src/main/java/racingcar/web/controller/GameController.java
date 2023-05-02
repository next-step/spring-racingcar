package racingcar.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.domain.dto.RacingGameResultDto;
import racingcar.domain.service.RacingCarService;
import racingcar.web.dto.PlaysRequest;
import racingcar.web.dto.PlaysResponse;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameController {

    private final RacingCarService racingCarService;

    @PostMapping(value = "/plays",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlaysResponse plays(@Valid @RequestBody PlaysRequest playsRequest) {
        log.info(playsRequest.toString());
        RacingGameResultDto gameResult = racingCarService.plays(playsRequest.getNames(),
                playsRequest.getCount());

        return PlaysResponse.from(gameResult);
    }
}
