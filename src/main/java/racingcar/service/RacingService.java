package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.RacingPlaysRequest;
import racingcar.dto.RacingPlaysResponse;
import racingcar.repository.RacingResultRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

        RacingCars racingCars = new RacingCars(racingPlaysRequest.getConvertRequestNameToCarList());

        racingCars.playRound(racingPlaysRequest.getCount());

        racingResultRepository.insertGameResult(
                PlayResult.builder()
                        .winners(racingCars.getWinnersToString())
                        .trialCount(racingPlaysRequest.getCount())
                        .racingCars(RacingCarResponse.listOf(racingCars.getRacingCars()))
                        .build()
        );

        PlayResult playResult =  racingResultRepository.getResult();

        return RacingPlaysResponse.of(playResult);

    }

    public List<RacingPlaysResponse> playRaceList(){

        List<PlayResult> playListResult =  racingResultRepository.getResultAll();

        return racingResultRepository.getResultAll().stream()
                .map(RacingPlaysResponse::of)
                .collect(Collectors.toList());

    }

}
