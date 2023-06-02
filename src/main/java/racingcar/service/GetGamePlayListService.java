package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingPlayerRepository;
import racingcar.usecase.GetGamePlayListUseCase;
import racingcar.usecase.response.GetGamePlayListResponse;

import java.util.List;

@Service
public class GetGamePlayListService implements GetGamePlayListUseCase {

    private final RacingPlayerRepository racingPlayerRepository;

    @Autowired
    public GetGamePlayListService(RacingPlayerRepository racingPlayerRepository) {
        this.racingPlayerRepository = racingPlayerRepository;
    }


    @Override
    public GetGamePlayListResponse getGamePlayList() {
        List<RacingPlayer> playerList = racingPlayerRepository.findAll();
        return new GetGamePlayListResponse(playerList);
    }
}
