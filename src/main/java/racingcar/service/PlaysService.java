package racingcar.service;

import racingcar.domain.RacingCar;
import racingcar.dtos.request.PlaysRequestDto;
import racingcar.dtos.response.PlaysResponseDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PlaysService {
    public PlaysResponseDto play(PlaysRequestDto playsRequestDto) {
        List<RacingCar> racingCars = Arrays.stream(playsRequestDto.getNames().split(",")).map(RacingCar::new).collect(Collectors.toList());
        playRound(racingCars, playsRequestDto.getCount());

        return new PlaysResponseDto( getWinners(racingCars), racingCars );
    }

    private void playRound(List<RacingCar> racingCars, Integer playCount) {
        Random random = new Random();
        for (int i = 0; i < playCount; i++) {
            racingCars.forEach(racingCar -> {
                int randomNumber = random.nextInt(10);
                racingCar.move(randomNumber);
            });
        }
    }

    private String getWinners(List<RacingCar> racingCars) {
        int maxPosition = 0;
        String winners  = "";
        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() >= maxPosition) {
                maxPosition = racingCar.getPosition();
                winners     = racingCar.getName();
            }
        }
        return winners;
    }
}
