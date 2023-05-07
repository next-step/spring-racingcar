package racingcar.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingGameResponse;
import racingcar.entity.RacingPlayer;
import racingcar.entity.RacingPlayerResponse;
import racingcar.service.CalculateRaceService;
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
    private final CalculateRaceService calculateRaceService;


    @Autowired
    public RacingGameFacadeImpl(RacingGameService racingGameService, RacingPlayerService racingPlayerService, CalculateRaceService calculateRaceService) {
        this.racingGameService = racingGameService;
        this.racingPlayerService = racingPlayerService;
        this.calculateRaceService = calculateRaceService;
    }


    @Override
    public CreateRacingGameResponse createRacingGame(String names, Integer trialCount) {

        List<String> nameList = this.parseName(names);

        RacingGame racingGame = racingGameService.createRacingGame(trialCount);

        List<Integer> positions = calculateRaceService.calculatePositions(nameList, racingGame.getTrialCount());

        List<RacingPlayer> racingPlayers = racingPlayerService.createRacingPlayers(nameList, racingGame, positions);

        return this.mapRacingGameResponse(racingGame, racingPlayers);
    }

    private List<String> parseName(String names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(names.replace(" ", "").split(",")).collect(Collectors.toList());
    }
    private CreateRacingGameResponse mapRacingGameResponse(RacingGame racingGame, List<RacingPlayer> racingPlayers) {
        RacingGameResponse response = new RacingGameResponse(racingGame.getId(), racingGame.getTrialCount());
        List<RacingPlayerResponse> responses = racingPlayers.stream().map(p -> new RacingPlayerResponse(p.getId(), p.getName(), p.getPosition(), p.isWinner())).collect(Collectors.toList());
        return new CreateRacingGameResponse(response, responses);
    }
}
