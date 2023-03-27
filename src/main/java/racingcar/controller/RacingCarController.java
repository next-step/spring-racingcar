package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.RacingService;
import racingcar.model.RacingResultIn;
import racingcar.model.RacingResultOut;

@RequestMapping(value = "/")
@RestController
public class RacingCarController {
    private final RacingService racingService;

    public RacingCarController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping(value = "/plays")

    public ResponseEntity<RacingResultOut> racingStart(@RequestBody RacingResultIn requestData) {

        RacingResultOut racingResult = new RacingResultOut();
        racingResult = racingService.racingStart(requestData);
        return new ResponseEntity<>(racingResult, HttpStatus.OK);
    }

}
