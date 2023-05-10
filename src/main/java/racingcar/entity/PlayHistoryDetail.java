package racingcar.entity;

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

    // TODO Dto 측에 toEntity()와 Entity를 생성자로 받는 메서드 생성해서 controller -> service -> dao로 이어지는 의존 방향 지키기
    public PlayHistoryDto.RacingCar toPlayHistoryDtoRacingCar() {
        return new PlayHistoryDto.RacingCar(name, position);
    }

}
