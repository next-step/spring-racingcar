package racingcar.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RacingGame;
import racingcar.api.request.PlayRequest;
import racingcar.api.response.PlayResponse;
import racingcar.service.RacingCarGameService;

import java.util.Random;

@RestController
public class RacingCarController {

    private final RacingCarGameService racingCarGameService;

    public RacingCarController(RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }

    @PostMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayResponse> plays(@RequestBody PlayRequest request) {

        RacingGame racingGame = new RacingGame(request.makeCars(), new Random());
        for (int i = 0; i < request.getCount(); i++) {
            racingGame.startRace();
        }

        racingCarGameService.saveResult(racingGame, request.getCount());

        return ResponseEntity.ok(PlayResponse.extract(racingGame.getParticipationCars(), racingGame.getRacingWinners()));
    }
}
