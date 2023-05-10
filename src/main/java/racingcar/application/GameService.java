package racingcar.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.application.dto.GameRequest;
import racingcar.application.dto.GameResponse;
import racingcar.domain.GameResult;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGameRepository;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class GameService {

    private static final int ZERO = 0;
    private static final String DELIMITER = ",";

    private final RacingGameRepository racingGameRepository;

    public GameService(RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional
    public GameResponse saveGameResult(GameRequest gameRequest) {
        RacingCars racingCars = RacingCars.of(gameRequest, DELIMITER);
        racingCardMove(racingCars, gameRequest.getCount());

        racingGameRepository.save(new GameResult(racingCars.getRacingCars(), gameRequest.getCount(), racingCars.getWinner(DELIMITER)));

        return GameResponse.of(racingCars.getWinner(DELIMITER), racingCars.getRacingCars());
    }

    private void racingCardMove(RacingCars racingCars, int trialCount) {
        IntStream.range(ZERO, trialCount)
                .forEach(index -> racingCars.move());
    }

    @Transactional(readOnly = true)
    public List<GameResponse> findResult() {
        return racingGameRepository.findAll();
    }

}
