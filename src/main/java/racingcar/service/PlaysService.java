package racingcar.service;

import racingcar.domain.RacingCar;
import racingcar.dtos.request.PlaysRequestDto;
import racingcar.dtos.response.PlaysResponseDto;
import org.springframework.stereotype.Service;
import racingcar.repository.PlayDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PlaysService {
    private final PlayDao playDao;
    private final Random random = new Random();

    public PlaysService(PlayDao playDao) {
        this.playDao = playDao;
    }

    public PlaysResponseDto play(PlaysRequestDto playsRequestDto) {
        List<RacingCar> racingCars = Arrays.stream(playsRequestDto.getNames().split(",")).map(RacingCar::new).collect(Collectors.toList());

        playRound(racingCars, playsRequestDto.getCount());
        List<String> winners = getWinners(racingCars);

        winners.forEach(winner -> playDao.insertWinner(winner, playsRequestDto.getCount()));
        racingCars.forEach(racingCar -> playDao.insertPlayTravelDistance(racingCar.getName(), racingCar.getPosition()));

        return new PlaysResponseDto( winners, racingCars );
    }

    private void playRound(List<RacingCar> racingCars, Integer playCount) {
        for (int i = 0; i < playCount; i++) {
            racingCars.forEach(racingCar -> racingCar.move(random.nextInt(10)));
        }
    }

    private List<String> getWinners(List<RacingCar> racingCars) {
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();

        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) { // maxPosition 보다 다음 racingCar의 position이 더 크니 기존 winnder들은 날려버려야함.
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.add(racingCar.getName());
            }
        }
        return winners;
    }
}
