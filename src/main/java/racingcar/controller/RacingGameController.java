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
import java.util.Arrays;
import java.util.Collections;
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
        List<String> nameList = parseName(request.getNames());
        CreateRacingGameResponse result = racingGameFacade.createRacingGame(nameList, count);
        List<String> responses = result.getPlayers().stream().filter(RacingPlayerResponse::getWinner).map(RacingPlayerResponse::getName).collect(Collectors.toList());
        String winners = String.join(", ", responses);
        List<ApiCreateRacingPlayerResponse> players = result.getPlayers().stream().map(r -> new ApiCreateRacingPlayerResponse(r.getName(), r.getPosition())).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiCreateRacingGameResponse(winners, players));
    }


    private List<String> parseName(String names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(names.replace(" ", "").split(",")).collect(Collectors.toList());
    }
}
