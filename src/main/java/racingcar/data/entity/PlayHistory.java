package racingcar.data.entity;

import racingcar.business.domain.RacingCar;

public class PlayHistory {
    long id;
    long playResultId;
    String playerName;
    long position;

    public PlayHistory(long id, long playResultId, String playerName, long position) {
        this.id = id;
        this.playResultId = playResultId;
        this.playerName = playerName;
        this.position = position;
    }

    public PlayHistory(long playResultId, RacingCar racingCar) {
        this.playResultId = playResultId;
        this.playerName = racingCar.getName();
        this.position = racingCar.getPosition();
    }

    public long getPlayResultId() {
        return playResultId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public long getPosition() {
        return position;
    }
}
