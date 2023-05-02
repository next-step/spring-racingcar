package racingcar.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingGameResponse;
import racingcar.entity.RacingPlayer;
import racingcar.entity.RacingPlayerResponse;
import racingcar.service.RacingGameService;
import racingcar.service.RacingPlayerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RacingGameFacadeImpl implements RacingGameFacade {

    private final RacingGameService racingGameService;
    private final RacingPlayerService racingPlayerService;

    @Autowired
    public RacingGameFacadeImpl(RacingGameService racingGameService, RacingPlayerService racingPlayerService) {
        this.racingGameService = racingGameService;
        this.racingPlayerService = racingPlayerService;
    }


    @Override
    public CreateRacingGameResponse createRacingGame(String names, Integer trialCount) {
        List<String> nameList = this.getNameList(names);
        RacingGame racingGame = racingGameService.createRacingGame(trialCount);
        List<Integer> positions = racingPlayerService.getPositions(nameList, racingGame.getTrialCount());
        Integer maxValue = racingPlayerService.getMaxValue(positions);

        List<RacingPlayer> racingPlayers = this.createRacingPlayers(nameList, racingGame, positions, maxValue);

        RacingGameResponse response = new RacingGameResponse(racingGame.getId(), racingGame.getTrialCount());
        List<RacingPlayerResponse> responses = racingPlayers.stream().map(p -> new RacingPlayerResponse(p.getId(), p.getName(), p.getPosition(), p.isWinner())).collect(Collectors.toList());
        return new CreateRacingGameResponse(response, responses);
    }

    private List<String> getNameList(String names) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(names.replace(" ", "").split(",")).collect(Collectors.toList());
    }

    private List<RacingPlayer> createRacingPlayers(List<String> nameList, RacingGame racingGame, List<Integer> positions, Integer maxValue) {
        return IntStream.range(0, nameList.size())
                .mapToObj(i -> racingPlayerService.createRacingPlayer(nameList.get(i), positions.get(i), racingPlayerService.isWinner(positions.get(i), maxValue), racingGame))
                .collect(Collectors.toList());
    }
}
