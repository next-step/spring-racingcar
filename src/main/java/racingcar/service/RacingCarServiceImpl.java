package racingcar.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.repository.RacingCarRepository;
@RequiredArgsConstructor
@Service
public class RacingCarServiceImpl implements RacingCarService {
  private final RacingCarRepository racingCarRepository;

  @Override
  public RacingResultDto playRacing(RacingRequestDto racingRequestDto) {
    // RacingCars 만들기
    String names = racingRequestDto.getNames();
    List<RacingCar> racingCars = this.makeRacingCars(names);

    // count 만큼 racing play
    int count = racingRequestDto.getCount();
    for (int i = 0 ; i < count ; i++) {
      this.playRacingGame(racingCars);
    }

    // 우승자 구하기
    String winners = this.getWinner(racingCars);

    return new RacingResultDto(winners, racingCars);
  }
  private String getWinner(List<RacingCar> racingCars) {
    // RacingCar 객체들을 position 속성값으로 내림차순으로 정렬
    racingCars.sort(Comparator.comparingInt(RacingCar::getPosition).reversed());
    racingCarRepository.insertRacingResult(racingCars.get(0).getName(), racingCars.get(0).getPosition());
    // position 속성값이 가장 큰 RacingCar 객체의 name 속성값 반환
    return racingCars.get(0).getName();
  }

  private void playRacingGame(List<RacingCar> racingCars) {
    //게임 돌리기
    Random random = new Random();
    for(RacingCar racingCar : racingCars){
      int ranDomNum = random.nextInt();
      racingCar.move(ranDomNum);
    }
  }

  private List<RacingCar> makeRacingCars(String names) {
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
