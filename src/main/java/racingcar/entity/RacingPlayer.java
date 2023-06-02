package racingcar.entity;

import racingcar.utils.annotation.GeneratedValue;
import racingcar.utils.annotation.IdField;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Predicate;

public class RacingPlayer extends BaseEntity {

    @IdField
    @GeneratedValue
    private Long id;
    private final RacingGame racingGame;
    private final String name;
    private final Integer position;
    private final Boolean isWinner;

    //생성함수

    public RacingPlayer(String name, int position, boolean isWinner, RacingGame racingGame) {
        this.name = name;
        this.position = position;
        this.racingGame = racingGame;
        this.isWinner = isWinner;
    }

    private RacingPlayer(long id, String name, int position, boolean isWinner, RacingGame racingGame) {
        this.id = id;
        this.racingGame = racingGame;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    private RacingPlayer(long id, String name, int position, boolean isWinner, RacingGame racingGame, LocalDateTime createdDate) {
        this.id = id;
        this.racingGame = racingGame;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.setCreatedDate(createdDate);
    }

    public Long getRacingGameId() {
        return this.racingGame.getId();
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public Boolean isWinner() {
        return isWinner;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public static class MockFactory {
    }

    public static class MappingFactory {
        public static RacingPlayer generate(long id, String name, int position, boolean isWinner, RacingGame racingGame, LocalDateTime createdDate) {
            return new RacingPlayer(id, name, position, isWinner, racingGame, createdDate);
        }
    }
}
