package racingcar.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
    // RacingCars 만들기
    String names = racingRequestDto.getNames();
    List<RacingCar> racingCars = this.makeRacingCars(names);

    // count 만큼 racing play
    int count = racingRequestDto.getCount();
    for (int i = 0; i < count; i++) {
      this.playRacingGame(racingCars);
    }

    // 우승자 구하기
    String winners = this.getWinner(racingCars);

    //레이싱 히스토리 적재
    RaceResult result = new RaceResult(winners, racingCars);
    racingCarRepository.insertRacingResult(result, LocalDateTime.now());
    System.out.println("히스토리 적재완료");

    return new RacingResultDto(winners, racingCars);
  }

  public List<RaceResult> findAll() {
    List<RaceResult> results = new ArrayList<>();
    List<RacingCar> racingCars = racingCarRepository.findAll();
    results.add(new RaceResult(getWinner(racingCars), racingCars));
    return results;
  }


  private String getWinner(List<RacingCar> racingCars) {
    // RacingCar 객체들을 position 속성값으로 내림차순으로 정렬
    racingCars.sort(Comparator.comparingInt(RacingCar::getPosition).reversed());

    // 우승자 이름 저장할 리스트
    List<String> winners = new ArrayList<>();
    int maxPosition = racingCars.get(0).getPosition();
    winners.add(racingCars.get(0).getName());

    for (int i = 1; i < racingCars.size(); i++) {
      RacingCar car = racingCars.get(i);
      if (car.getPosition() == maxPosition) {
        winners.add(car.getName());
      } else {
        break;
      }
    }

    // 우승자 이름을 쉼표로 구분하여 String 형태로 반환
    return String.join(",", winners);
  }


  private void playRacingGame(List<RacingCar> racingCars) {
    //게임 돌리기
    Random random = new Random();
    for (RacingCar racingCar : racingCars) {
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
