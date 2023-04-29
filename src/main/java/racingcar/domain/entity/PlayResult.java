package racingcar.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

public class PlayResult {
    private int id;
    private String winners;
    private int trialCount;
    private List<RacingCar> racingCars;
    private LocalDateTime createdAt;
}
