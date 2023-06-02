package racingcar.usecase.response;

import java.util.List;

public class GetGamePlayListResponse {
    List<RacingPlayerResponse> racingPlayerResponseList;

    public GetGamePlayListResponse(List<RacingPlayerResponse> racingPlayerResponseList) {
        this.racingPlayerResponseList = racingPlayerResponseList;
    }

    public List<RacingPlayerResponse> getRacingPlayerResponseList() {
        return racingPlayerResponseList;
    }
}
