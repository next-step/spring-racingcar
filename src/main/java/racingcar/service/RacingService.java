package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingPlaysRequest;
import racingcar.dto.RacingPlaysResponse;
import racingcar.repository.RacingResultRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Service
public class RacingService {
    private final RacingResultRepository racingResultRepository;

    public RacingService(RacingResultRepository racingResultRepository) {
        this.racingResultRepository = racingResultRepository;
    }

    public RacingPlaysResponse playRace(RacingPlaysRequest racingPlaysRequest){
        List<RacingCar> racingCars = convertRequestNameToCarList(racingPlaysRequest);

        playRound(racingCars, racingPlaysRequest.getCount());
        String winners = String.join(", ", getWinners(racingCars));

        racingResultRepository.insertGameResult(new PlayResult(winners, racingPlaysRequest.getCount(), racingCars));

        PlayResult playResult =  racingResultRepository.getResults();

        return RacingPlaysResponse.of(playResult);

    }


    private List<RacingCar> convertRequestNameToCarList(RacingPlaysRequest racingPlaysRequest){
        List<RacingCar> result = new ArrayList<>();
        Arrays.stream(racingPlaysRequest.getNames().split(",")).forEach(
                car -> result.add(new RacingCar(car))
        );
        return result;
    }

    private static void playRound(List<RacingCar> racingCars, int requestRound) {

        Random random = new Random();
        for (int i = 0; i < requestRound; i++) {
            for (RacingCar racingCar : racingCars) {
                int randomNumber = random.nextInt(10);
                racingCar.move(randomNumber);
            }
        }

    }

    private List<String> getWinners(List<RacingCar> racingCars){
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();
        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }
        return winners;
    }

}
