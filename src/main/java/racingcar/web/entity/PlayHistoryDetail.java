package racingcar.web.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import racingcar.web.dto.PlayHistoryDto;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class PlayHistoryDetail {

    private long id;
    private long playHistoryId;
    private String name;
    private int position;

    public PlayHistoryDetail(long playHistoryId, String name, int position) {
        this.playHistoryId = playHistoryId;
        this.name = name;
        this.position = position;
    }

    public PlayHistoryDetail(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public PlayHistoryDto.RacingCar toPlayHistoryDtoRacingCar() {
        return new PlayHistoryDto.RacingCar(name, position);
    }

}
