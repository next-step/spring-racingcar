package racingcar.game.web.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.application.dto.Player;

@RequiredArgsConstructor
@Getter
public class RacingCarResponse {

    private final String name;
    private final int position;

    public static RacingCarResponse from(Player player) {
        return new RacingCarResponse(player.getName(), player.getPosition());
    }

    public static List<RacingCarResponse> from(List<Player> playerRacingCars) {
        return playerRacingCars.stream()
            .map(RacingCarResponse::from)
            .collect(Collectors.toUnmodifiableList());
    }
}
