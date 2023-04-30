package racingcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.service.RacingService;

@RestController
@RequiredArgsConstructor
public class RacingController {

    private final RacingService racingService;

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> request(@RequestBody RacingRequest racingRequest) {
        RacingResponse response = racingService.playGame(racingRequest);
        return ResponseEntity.ok().body(response);
    }

}
