package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import static racingcar.Application.racingCarService;

@RestController
public class RacingCarController {

    private RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> racingGame(@RequestBody RacingRequest racingRequest) {
        racingRequest.validate();

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
