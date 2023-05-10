package racingcar.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingGameResponse;
import racingcar.entity.RacingPlayer;
import racingcar.entity.RacingPlayerResponse;
import racingcar.service.RandomNumberGenerator;
import racingcar.service.RacingGameService;
import racingcar.service.RacingPlayerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RacingGameFacadeImpl implements RacingGameFacade {

    private final RacingGameService racingGameService;
    private final RacingPlayerService racingPlayerService;
    private final RandomNumberGenerator randomNumberGenerator;


    @Autowired
    public RacingGameFacadeImpl(RacingGameService racingGameService, RacingPlayerService racingPlayerService, RandomNumberGenerator randomNumberGenerator) {
        this.racingGameService = racingGameService;
        this.racingPlayerService = racingPlayerService;
        this.randomNumberGenerator = randomNumberGenerator;
    }


    @Override
    public CreateRacingGameResponse createRacingGame(List<String> nameList, Integer trialCount) {

        RacingGame racingGame = racingGameService.createRacingGame(trialCount);

        List<Integer> positions = randomNumberGenerator.calculatePositions(nameList.size(), racingGame.getTrialCount());

        List<RacingPlayer> racingPlayers = racingPlayerService.createRacingPlayers(nameList, racingGame, positions);

        return this.mapRacingGameResponse(racingGame, racingPlayers);
    }

    private CreateRacingGameResponse mapRacingGameResponse(RacingGame racingGame, List<RacingPlayer> racingPlayers) {
        RacingGameResponse response = new RacingGameResponse(racingGame.getId(), racingGame.getTrialCount());
        List<RacingPlayerResponse> responses = racingPlayers.stream().map(p -> new RacingPlayerResponse(p.getId(), p.getName(), p.getPosition(), p.isWinner())).collect(Collectors.toList());
        return new CreateRacingGameResponse(response, responses);
    }
}
