package racingcar.game.application;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.game.dao.PlayResultDao;
import racingcar.game.dao.PlayerDao;
import racingcar.game.domain.Player;
import racingcar.game.domain.RacingGame;
import racingcar.game.domain.PlayResult;
import racingcar.game.domain.RandomMoveStrategy;
import racingcar.game.dto.PlayRequest;
import racingcar.game.dto.PlayResultResponse;

@Service
public class GameService {

    private final PlayResultDao playResultDao;
    private final PlayerDao playerDao;

    public GameService(PlayResultDao playResultDao, PlayerDao playerDao) {
        this.playResultDao = playResultDao;
        this.playerDao = playerDao;
    }

    @Transactional
    public PlayResultResponse play(PlayRequest playRequest) {
        RacingGame racingGame = playRequest.toRacingGame();
        racingGame.playRound(playRequest.getCount(), new RandomMoveStrategy());

        Long savedId = playResultDao.save(PlayResult.of(playRequest.getCount()));
        playerDao.saveAll(converterPlayers(savedId, racingGame));
        return PlayResultResponse.from(racingGame);
    }

    private List<Player> converterPlayers(Long playResultId, RacingGame racingGame) {
        return racingGame.getRacingCars()
            .stream()
            .map(racingCar -> Player.of(playResultId, racingCar, racingGame.isWinner(racingCar.getName())))
            .collect(Collectors.toUnmodifiableList());
    }
}
