package racingcar;

import org.springframework.stereotype.Repository;
import racingcar.usecase.GetGamePlayListUseCase;
import racingcar.usecase.response.GetGamePlayListResponse;

public class GetGamePlayListDummy implements GetGamePlayListUseCase {

    @Override
    public GetGamePlayListResponse getGamePlayList() {
        return null;
    }
}
