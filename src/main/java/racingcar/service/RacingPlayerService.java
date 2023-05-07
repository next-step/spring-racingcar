package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingPlayerRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RacingPlayerService {
    private final RacingPlayerRepository racingPlayerRepository;


    @Autowired
    public RacingPlayerService(RacingPlayerRepository racingPlayerRepository) {
        this.racingPlayerRepository = racingPlayerRepository;
    }

    public List<RacingPlayer> createRacingPlayers(List<String> nameList, RacingGame racingGame, List<Integer> positions) {
        List<Boolean> separateWinners = this.separateWinners(positions);

        return IntStream.range(0, nameList.size())
                .mapToObj(i -> this.createRacingPlayer(nameList.get(i), positions.get(i), separateWinners.get(i), racingGame))
                .collect(Collectors.toList());
    }


    /**
     * 레이싱 플레이어를 생성하고 db에 저장한다.
     * @param name 플레이어 이름
     * @param position 플레이어 위치
     * @param isWinner 플레이어 우승 여부
     * @param racingGame 레이싱 게임
     * @return 레이싱 플레이어
     */
    public RacingPlayer createRacingPlayer(String name, Integer position, Boolean isWinner, RacingGame racingGame) {
        RacingPlayer racingPlayer = new RacingPlayer(name, position, isWinner);
        racingPlayer.injectRacingGameId(racingGame.getId());
        return racingPlayerRepository.save(racingPlayer);
    }

    public boolean isWinner(int position, List<Integer> positions) {
        Integer maxValue = this.calculateConditionOfVictory(positions);
        return maxValue == position;
    }

    public List<Boolean> separateWinners(List<Integer> positions) {
        Integer maxValue = this.calculateConditionOfVictory(positions);
        return positions.stream().map(position -> Objects.equals(position, maxValue)).collect(Collectors.toList());
    }

    /**
     * 레이싱 결과물 중 최댓값을 구한다.
     * @param distances 레이싱 결과물
     * @return 최댓값
     */
    public Integer calculateConditionOfVictory(List<Integer> distances) {
        return distances.stream().max(Integer::compareTo).orElseThrow(RuntimeException::new);
    }

}
