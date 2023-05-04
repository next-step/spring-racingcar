package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.RacingPlaysRequest;
import racingcar.dto.RacingPlaysResponse;
import racingcar.repository.RacingResultRepository;

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
        List<RacingCar> racingCars = racingPlaysRequest.getConvertRequestNameToCarList();

        playRound(racingCars, racingPlaysRequest.getCount());

        racingResultRepository.insertGameResult(
                PlayResult.builder()
                        .trialCount(racingPlaysRequest.getCount())
                        .racingCars(RacingCarResponse.listOf(racingCars))
                        .build()
        );

        PlayResult playResult =  racingResultRepository.getResults();

        return RacingPlaysResponse.of(playResult);

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

}
