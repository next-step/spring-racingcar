package racingcar.domain.dto;

import racingcar.domain.Name;
import racingcar.domain.Position;
import racingcar.entity.PlayHistoryDetail;

public class PlayResultDto {

    private final Position position;
    private final Name name;

    public PlayResultDto(int position, String name) {
        this(new Position(position), new Name(name));
    }

    public PlayResultDto(Position position, Name name) {
        this.position = position;
        this.name = name;
    }

    public boolean isPositionEquals(Position position) {
        return this.position.equals(position);
    }

    public Position getPosition() {
        return position;
    }

    public Name getName() {
        return name;
    }

    public int getPositionValue() {
        return position.getPosition();
    }

    public String getNameValue() {
        return name.getName();
    }

    public PlayHistoryDetail toPlayHistoryDetail(Long playHistoryId) {
        return new PlayHistoryDetail(playHistoryId, getNameValue(), getPositionValue());
    }

}
