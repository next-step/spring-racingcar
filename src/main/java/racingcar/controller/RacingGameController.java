package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.controller.request.ApiCreateRacingGameRequest;
import racingcar.controller.response.ApiCreateRacingGameResponse;
import racingcar.controller.response.ApiGetGamePlayListResponse;
import racingcar.entity.RacingPlayer;
import racingcar.usecase.GetGamePlayListUseCase;
import racingcar.usecase.response.GetGamePlayListResponse;
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

    @GetMapping("/plays")
    public ResponseEntity<List<ApiGetGamePlayListResponse>> getRacingGameList() {

        GetGamePlayListResponse response = getGamePlayListUseCase.getGamePlayList();

        List<ApiGetGamePlayListResponse> result = response.getGameList().stream().map(this::convertResponse).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    private ApiGetGamePlayListResponse convertResponse(GetGamePlayListResponse.GameWithPlayer response) {
        List<String> responses = response.getPlayerList().stream().filter(RacingPlayer::isWinner).map(RacingPlayer::getName).collect(Collectors.toList());
        String winners = String.join(", ", responses);
        List<ApiGetGamePlayListResponse.PlayerResponse> players = response.getPlayerList().stream().map(r -> new ApiGetGamePlayListResponse.PlayerResponse(r.getName(), r.getPosition())).collect(Collectors.toList());

        return new ApiGetGamePlayListResponse(winners, players);
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
        List<String> responses = result.getPlayerList().stream().filter(RacingPlayer::isWinner).map(RacingPlayer::getName).collect(Collectors.toList());
        String winners = String.join(", ", responses);
        List<ApiCreateRacingGameResponse.PlayerResponse> players = result.getPlayerList().stream().map(r -> new ApiCreateRacingGameResponse.PlayerResponse(r.getName(), r.getPosition())).collect(Collectors.toList());

        return new ApiCreateRacingGameResponse(winners, players);
    }

    private List<String> parseName(String names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(names.replace(" ", "").split(",")).collect(Collectors.toList());
    }
}
