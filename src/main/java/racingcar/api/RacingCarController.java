package racingcar.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarMoveDeterminer;
import racingcar.domain.RacingGame;
import racingcar.api.request.PlayRequest;
import racingcar.api.response.PlayResponse;
import racingcar.service.RacingCarGameService;

import java.util.List;

@RestController
public class RacingCarController {

    private final RacingCarGameService racingCarGameService;
    private final CarMoveDeterminer carMoveDeterminer;

    public RacingCarController(RacingCarGameService racingCarGameService, CarMoveDeterminer carMoveDeterminer) {
        this.racingCarGameService = racingCarGameService;
        this.carMoveDeterminer = carMoveDeterminer;
    }

    @PostMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayResponse> plays(@RequestBody PlayRequest request) {
        RacingGame racingGame = new RacingGame(request.makeCars(), carMoveDeterminer);
        return ResponseEntity.ok(racingCarGameService.play(racingGame, request.getCount()));
    }

    @GetMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayResponse>> getAllRacingGames() {
        return ResponseEntity.ok(racingCarGameService.getAllRacingGames());
    }
}
