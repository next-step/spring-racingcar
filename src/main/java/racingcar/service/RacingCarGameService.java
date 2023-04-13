package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.api.response.PlayResponse;
import racingcar.domain.RacingGame;
import racingcar.domain.ResultPlay;
import racingcar.repository.RacingCarGameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RacingCarGameService {
    private final RacingCarGameRepository racingCarGameRepository;

    public RacingCarGameService(RacingCarGameRepository racingCarGameRepository) {
        this.racingCarGameRepository = racingCarGameRepository;
    }

    public PlayResponse play(RacingGame racingGame, int count) {
        racingGame.startGame(count);
        this.saveResult(racingGame);

        return PlayResponse.extract(racingGame.getParticipationCars(), racingGame.getRacingWinners());
    }

    private void saveResult(RacingGame racingGame) {
        long playId = racingCarGameRepository.saveResult(racingGame);
        racingCarGameRepository.saveDetailResult(playId, racingGame);
    }
    public List<PlayResponse> getAllRacingGames() {
        List<ResultPlay> resultPlays = racingCarGameRepository.racingGameResults();
        return resultPlays.stream()
                .map(racingCarGameRepository::racingGameDetailResults)
                .map((racingGame) -> PlayResponse.extract(racingGame.getParticipationCars(), racingGame.getRacingWinners()))
                .collect(Collectors.toList());
    }
}

