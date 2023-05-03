package racingcar.domain.service;

import org.springframework.stereotype.Service;
import racingcar.domain.entity.PlayResult;
import racingcar.domain.entity.RacingCars;
import racingcar.domain.repository.PlayResultRepository;

import java.util.List;

@Service
public class RacingCarGameService {

    private PlayResultRepository playResultRepository;

    public RacingCarGameService(PlayResultRepository playResultRepository) {
        this.playResultRepository = playResultRepository;
    }

    public PlayResult playGame(RacingCars racingCars, int count) {
        RacingCars result = playRounds(racingCars, count);
        PlayResult playResult = PlayResult.of(result, count);
        playResultRepository.insert(playResult);
        return playResult;
    }

    public RacingCars playRounds(RacingCars racingCars, int count) {
        for (int i = 0; i < count; i++) {
            racingCars.playRound();
        }
        return racingCars;
    }

    public List<PlayResult> getPlayResults() {
        return playResultRepository.getAllPlayResults();
    }
}
