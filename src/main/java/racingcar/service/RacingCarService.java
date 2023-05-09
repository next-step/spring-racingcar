package racingcar.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.domain.RaceResult;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.repository.RacingCarRepository;

@RequiredArgsConstructor
@Service
public class RacingCarService {

  private final RacingCarRepository racingCarRepository;

  public RacingResultDto playRacing(RacingRequestDto racingRequestDto) {
    String names = racingRequestDto.getNames();
    List<RacingCar> racingCars = makeRacingCars(names);

    int count = racingRequestDto.getCount();
    for (int i = 0; i < count; i++) {
      playRound(racingCars);
    }

    RaceResult result = new RaceResult(racingCars);
    racingCarRepository.insertRacingResult(result, LocalDateTime.now());

    return new RacingResultDto(result.getWinner(racingCars), result.getRacingCars());
  }

  public List<RaceResult> findAll() {
    return racingCarRepository.findAll();
  }


  public static void playRound(List<RacingCar> racingCars) {
    //게임 돌리기
    Random random = new Random();
    for (RacingCar racingCar : racingCars) {
      int randomNumber = random.nextInt(10);
      racingCar.move(randomNumber);
    }
  }

  public static List<RacingCar> makeRacingCars(String names) {
    String[] carNames = names.split(",");
    List<RacingCar> racingCars = new ArrayList<>();

    for (String carName : carNames) {
      String trimmedName = carName.trim();
      RacingCar racingCar = new RacingCar(trimmedName);
      racingCars.add(racingCar);
    }
    return racingCars;
  }

}
