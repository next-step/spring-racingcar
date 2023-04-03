package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlaysReq;
import racingcar.dto.PlaysRes;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {
    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }



    @PostMapping(value="/plays", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaysRes> plays(@RequestBody PlaysReq playsReq) {
        PlaysRes playsRes = racingCarService.playRacing(playsReq);
        return ResponseEntity.ok().body(playsRes);

    }

}
