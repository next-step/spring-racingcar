package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingPlayerRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RacingPlayerService {
    private final RacingPlayerRepository racingPlayerRepository;


    @Autowired
    public RacingPlayerService(RacingPlayerRepository racingPlayerRepository) {
        this.racingPlayerRepository = racingPlayerRepository;
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
        racingPlayerRepository.save(racingPlayer);
        return racingPlayer;
    }

    public boolean isWinner(int position, int maxValue) {
        return maxValue == position;
    }

    /**
     * 레이싱 결과물 중 최댓값을 구한다.
     * @param distances 레이싱 결과물
     * @return 최댓값
     */
    public Integer getMaxValue(List<Integer> distances) {
        return distances.stream().max(Integer::compareTo).orElseThrow(RuntimeException::new);
    }

}
