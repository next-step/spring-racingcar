package racingcar.business;

import org.springframework.stereotype.Service;
import racingcar.RacingCars;
import racingcar.data.RacingRepository;
import racingcar.presentation.dto.GameResultDto;
import racingcar.presentation.dto.GameStartDto;

@Service
public class RacingService {

    private final RacingRepository racingRepository;

    public RacingService(RacingRepository racingRepository) {
        this.racingRepository = racingRepository;
    }

    public GameResultDto game(GameStartDto gameStartDto) {
        RacingCars racingCars = new RacingCars(gameStartDto.getNames());
        int count = gameStartDto.getCount();
        racingCars.startCarRacingGame(count);
        racingRepository.insertGameResult(racingCars);
        return new GameResultDto(racingCars);
    }

}