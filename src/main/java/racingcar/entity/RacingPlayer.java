package racingcar.entity;

import racingcar.utils.annotation.GeneratedValue;
import racingcar.utils.annotation.IdField;

import javax.validation.constraints.NotNull;

public class RacingPlayer extends BaseEntity {

    @IdField
    @GeneratedValue
    private Long id;
    private Long racingGameId;
    @NotNull
    private final String name;
    @NotNull
    private final Integer position;
    @NotNull
    private final Boolean isWinner;

    public RacingPlayer(String name, Integer position, Boolean isWinner) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public void injectRacingGameId(Long racingGameId) {
        this.racingGameId = racingGameId;
    }

    public Long getRacingGameId() {
        return racingGameId;
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
}
