package racingcar.entity;

import racingcar.utils.annotation.GeneratedValue;
import racingcar.utils.annotation.IdField;

import javax.validation.constraints.NotNull;
import java.util.function.Predicate;

public class RacingGame extends BaseEntity {

    @IdField
    @GeneratedValue
    private Long id;
    private final Integer trialCount;

    private Predicate<Integer> isWinner;

    // 생성 함수
    private RacingGame(int trialCount) {
        this.trialCount = trialCount;
    }

    private RacingGame(Long id, Integer trialCount, Predicate<Integer> winnerCondition) {
        this.id = id;
        this.trialCount = trialCount;
        this.isWinner = winnerCondition;
    }

    public static RacingGame create(int trialCount) {
        return new RacingGame(trialCount);
    }
    public RacingPlayer createRacingPlayer(String name, int position) {
        return new RacingPlayer(name, position, this.isWinner(position), this.id);
    }

    public void updateWinnerCondition(int condition) {
        this.isWinner = result -> result == condition;
    }

    public void updateWinnerCondition(Predicate<Integer> winnerCondition) {
        this.isWinner = winnerCondition;
    }

    public boolean isWinner(int maxValue) {
        return isWinner.test(maxValue);
    }

    public Integer getTrialCount() {
        return trialCount;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
    public static class MockFactory {
        public static RacingGame generate(Long id, Integer trialCount, int condition) {
            return new RacingGame(id, trialCount, result -> result == condition);
        }

        public static RacingGame generate(Long id, RacingGame racingGame) {
            return new RacingGame(id, racingGame.trialCount, racingGame.isWinner);
        }
    }
}
