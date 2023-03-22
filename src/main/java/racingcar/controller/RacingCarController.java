package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import racingcar.model.RacingResponse;
import racingcar.model.RacingRequest;
import racingcar.service.RacingCarService;

@RestController
@ComponentScan
public abstract class RacingCarController {
    @Autowired
    private RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> racingGame(RacingRequest racingRequest) {
        System.out.println("+++++++++ test");
        return ResponseEntity.ok(racingCarService.startRacing(racingRequest));
    }

    @GetMapping("/plays")
    public ResponseEntity<RacingResponse> racingGame2(RacingRequest racingRequest) {
        System.out.println("+++++++++ getgetget");
        return ResponseEntity.ok(racingCarService.startRacing(racingRequest));
    }

}
