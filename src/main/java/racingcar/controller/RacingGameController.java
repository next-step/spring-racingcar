package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import racingcar.entity.RacingPlayerResponse;
import racingcar.facade.CreateRacingGameResponse;
import racingcar.facade.RacingGameFacade;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingGameController {
    
    private final RacingGameFacade racingGameFacade;

    public RacingGameController(RacingGameFacade racingGameFacade) {
        this.racingGameFacade = racingGameFacade;
    }

    @PostMapping("/plays")
    public ResponseEntity<ApiCreateRacingGameResponse> createRacingGame(@Valid @RequestBody ApiCreateRacingGameRequest request) {
        Integer count = request.getCount();
        String names = request.getNames();
        CreateRacingGameResponse result = racingGameFacade.createRacingGame(names, count);
        List<String> reponses = result.getPlayers().stream().filter(RacingPlayerResponse::getWinner).map(RacingPlayerResponse::getName).collect(Collectors.toList());
        String winners = String.join(", ", reponses);
        List<ApiCreateRacingPlayerResponse> players = result.getPlayers().stream().map(r -> new ApiCreateRacingPlayerResponse(r.getName(), r.getPosition())).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiCreateRacingGameResponse(winners, players));
    }
}
