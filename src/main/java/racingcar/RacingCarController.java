package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.model.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class RacingCarController {

    private RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> racingGame(@Valid @RequestBody RacingRequest racingRequest) {

        RacingResponse racingResponse = racingCarService.racingGame(
                racingRequest.getNames(),
                racingRequest.getCount());

        return ResponseEntity.ok(racingResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResponse>> racingGameHistory() {

        List<RacingResponse> racingResponses = racingCarService.getRacingGameHistory();

        return ResponseEntity.ok(racingResponses);
    }
}
