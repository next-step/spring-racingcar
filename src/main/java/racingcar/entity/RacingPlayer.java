package racingcar.entity;

public class RacingPlayer extends BaseEntity {
    private Long racingGameId;
    private final String name;
    private final Integer position;
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

    public Boolean getWinner() {
        return isWinner;
    }
}
