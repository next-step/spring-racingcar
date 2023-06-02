package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.controller.request.ApiCreateRacingGameRequest;
import racingcar.controller.response.ApiCreateRacingGameResponse;
import racingcar.controller.response.ApiCreateRacingGameResponse.PlayerResponse;
import racingcar.usecase.GetGamePlayListUseCase;
import racingcar.usecase.response.GetGamePlayListResponse;
import racingcar.usecase.response.RacingPlayerResponse;
import racingcar.usecase.request.PlayRacingGameRequest;
import racingcar.usecase.response.PlayRacingGameResponse;
import racingcar.usecase.PlayRacingGameUseCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingGameController {
    
    private final PlayRacingGameUseCase playRacingGameUseCase;
    private final GetGamePlayListUseCase getGamePlayListUseCase;

    public RacingGameController(PlayRacingGameUseCase playRacingGameUseCase, GetGamePlayListUseCase getGamePlayListUseCase) {
        this.playRacingGameUseCase = playRacingGameUseCase;
        this.getGamePlayListUseCase = getGamePlayListUseCase;
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
        List<ApiCreateRacingGameResponse.PlayerResponse> players = result.getPlayers().stream().map(r -> new ApiCreateRacingGameResponse.PlayerResponse(r.getName(), r.getPosition())).collect(Collectors.toList());

        return new ApiCreateRacingGameResponse(winners, players);
    }

    private List<String> parseName(String names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(names.replace(" ", "").split(",")).collect(Collectors.toList());
    }
}
