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
import java.util.stream.IntStream;

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
        List<String> nameList = this.getNameList(names);
        RacingGame racingGame = racingGameService.createRacingGame(trialCount);
        List<Integer> positions = this.getPositions(nameList, racingGame.getTrialCount());
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


    /**
     * 레이싱 결과물을 구한다.
     * @param names 플레이어 이름
     * @param count 차수
     * @return 레이싱 결과물
     */
    public List<Integer> getPositions(List<String> names, int count) {
        return IntStream.range(0, names.size()).mapToObj(i -> calculateRaceService.getPosition(count)).collect(Collectors.toList());
    }
}
