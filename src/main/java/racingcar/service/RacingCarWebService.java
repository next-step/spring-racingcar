package racingcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingResultResponse;
import racingcar.dto.RacingStartDto;
import racingcar.jdbctemplate.InsertDao;
import racingcar.jdbctemplate.QueryDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RacingCarWebService {
    private final InsertDao insertDao;
    private final QueryDao queryDao;
    private final RacingCarService racingCarService;

    public RacingResultResponse playRacingWebGame(RacingStartDto racingStartDto) {

        RacingCar racingCar = racingCarService.playRacingGame(racingStartDto, queryDao.getNextRound());

        insertDao.insertRacingHistory(racingCar, racingStartDto.getTrial());
        insertDao.insertWinnerHistory(racingCar);

        return new RacingResultResponse(
                racingCar.getWinner().getCarNames(),
                racingCar.getCars()
        );
    }

    public List<RacingResultResponse> getRacingGame() {
        return queryDao.getPlayHistory();
    }
}
