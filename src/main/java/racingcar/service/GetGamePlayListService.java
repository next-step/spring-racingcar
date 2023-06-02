package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.repository.RacingPlayerRepository;
import racingcar.usecase.GetGamePlayListUseCase;
import racingcar.usecase.response.GetGamePlayListResponse;

@Service
public class GetGamePlayListService implements GetGamePlayListUseCase {

    private final RacingPlayerRepository racingPlayerRepository;

    @Autowired
    public GetGamePlayListService(RacingPlayerRepository racingPlayerRepository) {
        this.racingPlayerRepository = racingPlayerRepository;
    }


    @Override
    public GetGamePlayListResponse getGamePlayList() {

        return null;
    }
}
