package racingcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.service.RacingService;
import java.util.List;

@RestController
@RequestMapping("/plays")
@RequiredArgsConstructor
public class RacingController {

    private final RacingService racingService;

    @PostMapping
    public ResponseEntity<RacingResponse> request(@RequestBody RacingRequest racingRequest) {
        RacingResponse response = racingService.playGame(racingRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<RacingResponse>> get() {
        List<RacingResponse> responses = racingService.getPlayGameList();
        return ResponseEntity.ok().body(responses);
    }
}
