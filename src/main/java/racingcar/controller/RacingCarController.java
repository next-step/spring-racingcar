package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.request.PlayRacingCarRequest;
import racingcar.controller.dto.response.PlayRacingCarResponse;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayRacingCarResponse> play(@RequestBody PlayRacingCarRequest request) {
        PlayRacingCarResponse response = racingCarService.play(request);

        return ResponseEntity.ok(response);
    }
}
