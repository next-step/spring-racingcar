package racingcar.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.domain.RacingCar;
import racingcar.web.dto.PlaysRequest;

@Slf4j
@Controller
public class GameController {

    @PostMapping(value = "/plays",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RacingCar plays(@RequestBody PlaysRequest playsRequest) {
        log.info(playsRequest.toString());
        return null;
    }
}
