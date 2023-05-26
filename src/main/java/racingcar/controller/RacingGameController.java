package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.request.ApiCreateRacingGameRequest;
import racingcar.controller.response.ApiCreateRacingGameResponse;
import racingcar.controller.response.ApiCreateRacingPlayerResponse;
import racingcar.entity.RacingPlayerResponse;
import racingcar.service.request.PlayRacingGameRequest;
import racingcar.service.response.PlayRacingGameResponse;
import racingcar.service.PlayRacingGameService;
import racingcar.usecase.PlayRacingGameUseCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingGameController {
    
    private final PlayRacingGameUseCase playRacingGameUseCase;

    public RacingGameController(PlayRacingGameUseCase playRacingGameUseCase) {
        this.playRacingGameUseCase = playRacingGameUseCase;
    }

    @PostMapping("/plays")
    public ResponseEntity<ApiCreateRacingGameResponse> createRacingGame(@RequestBody ApiCreateRacingGameRequest apiRequest) {
        Integer count = apiRequest.getCount();
        List<String> nameList = this.parseName(apiRequest.getNames());
        PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, count);

        PlayRacingGameResponse result = playRacingGameUseCase.playRacingGame(request);

        ApiCreateRacingGameResponse response = this.convertResponse(result);

        return ResponseEntity.ok(response);
    }

    private ApiCreateRacingGameResponse convertResponse(PlayRacingGameResponse result) {
        List<String> responses = result.getPlayers().stream().filter(RacingPlayerResponse::getWinner).map(RacingPlayerResponse::getName).collect(Collectors.toList());
        String winners = String.join(", ", responses);
        List<ApiCreateRacingPlayerResponse> players = result.getPlayers().stream().map(r -> new ApiCreateRacingPlayerResponse(r.getName(), r.getPosition())).collect(Collectors.toList());

        return new ApiCreateRacingGameResponse(winners, players);
    }

    private List<String> parseName(String names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(names.replace(" ", "").split(",")).collect(Collectors.toList());
    }
}
