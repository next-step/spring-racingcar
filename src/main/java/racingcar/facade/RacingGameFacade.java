package racingcar.facade;

import java.util.List;

public interface RacingGameFacade {

    CreateRacingGameResponse createRacingGame(List<String> nameList, Integer trialCount);
}
