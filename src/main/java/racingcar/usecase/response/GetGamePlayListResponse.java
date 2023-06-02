package racingcar.usecase.response;

import racingcar.entity.RacingPlayer;

import java.util.List;

public class GetGamePlayListResponse {
    List<RacingPlayer> racingPlayerList;

    public GetGamePlayListResponse(List<RacingPlayer> racingPlayerList) {
        this.racingPlayerList = racingPlayerList;
    }

    public List<RacingPlayer> getRacingPlayerList() {
        return racingPlayerList;
    }
}
