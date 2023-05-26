package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingGameResponse;
import racingcar.entity.RacingPlayer;
import racingcar.entity.RacingPlayerResponse;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingPlayerRepository;
import racingcar.service.request.PlayRacingGameRequest;
import racingcar.service.response.PlayRacingGameResponse;
import racingcar.usecase.PlayRacingGameUseCase;
import racingcar.utils.generator.RandomNumberGenerator;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Validated
public class PlayRacingGameService implements PlayRacingGameUseCase {

    private final RandomNumberGenerator randomNumberGenerator;
    private final RacingGameRepository racingGameRepository;
    private final RacingPlayerRepository racingPlayerRepository;


    @Autowired
    public PlayRacingGameService(RacingPlayerRepository racingPlayerRepository, RandomNumberGenerator randomNumberGenerator, RacingGameRepository racingGameRepository) {
        this.racingPlayerRepository = racingPlayerRepository;
        this.randomNumberGenerator = randomNumberGenerator;
        this.racingGameRepository = racingGameRepository;
    }

    @Override
    public PlayRacingGameResponse playRacingGame(@Valid PlayRacingGameRequest request) {

        List<String> nameList = request.getNameList();
        int trialCount = request.getTrialCount();

        RacingGame racingGame = RacingGame.create(trialCount);

        racingGameRepository.save(racingGame);

        List<Integer> positions = randomNumberGenerator.calculatePositions(nameList.size(), racingGame.getTrialCount());

        int condition = positions.stream().max(Integer::compareTo).orElse(0);

        racingGame.updateWinnerCondition(condition);

        List<RacingPlayer> racingPlayers = this.createRacingPlayers(nameList, racingGame, positions);


        racingPlayers.forEach(racingPlayerRepository::save);

        return this.mapRacingGameResponse(racingGame, racingPlayers);
    }

    private List<RacingPlayer> createRacingPlayers(List<String> nameList, RacingGame racingGame, List<Integer> positions) {
        return IntStream.range(0, nameList.size())
                .mapToObj(i -> racingGame.createRacingPlayer(nameList.get(i), positions.get(i)))
                .collect(Collectors.toList());
    }

    private PlayRacingGameResponse mapRacingGameResponse(RacingGame racingGame, List<RacingPlayer> racingPlayers) {
        RacingGameResponse response = new RacingGameResponse(racingGame.getId(), racingGame.getTrialCount());
        List<RacingPlayerResponse> responses = racingPlayers.stream().map(p -> new RacingPlayerResponse(p.getId(), p.getName(), p.getPosition(), p.isWinner())).collect(Collectors.toList());
        return new PlayRacingGameResponse(response, responses);
    }
}
