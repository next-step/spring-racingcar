package racingcar.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.api.dto.RacingCarRequest;
import racingcar.api.dto.RacingCarResponse;

@RestController
public class RacingCarController {

    @PostMapping("/plays")
    public RacingCarResponse play(@RequestBody RacingCarRequest request) {
        return "play";
    }
}
