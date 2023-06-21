package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingPlayerRepository;
import racingcar.usecase.GetGamePlayListUseCase;
import racingcar.usecase.response.GetGamePlayListResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<Long, List<RacingPlayer>> gameIdToPlayerListMap = playerList.stream().collect(Collectors.groupingBy(RacingPlayer::getRacingGameId));
        List<GetGamePlayListResponse.GameWithPlayer> gameWithPlayerList = gameIdToPlayerListMap.entrySet().stream().map(entry -> new GetGamePlayListResponse.GameWithPlayer(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        return new GetGamePlayListResponse(gameWithPlayerList);
    }
}
