package racingcar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.model.RacingResponse;
import racingcar.model.RacingRequest;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {
    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> racingGame(@RequestBody RacingRequest racingRequest) {
        RacingResponse racingResponse = racingCarService.startRacing(racingRequest.getNames(),
                racingRequest.getCount());
        return ResponseEntity.ok().body(racingResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResponse>> racingHistory() {
        List<RacingResponse> racingHistory = racingCarService.getRacingHistory();
        return ResponseEntity.ok().body(racingHistory);
    }
}
