package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.RacingCar;
import racingcar.dtos.request.PlaysRequestDto;
import racingcar.dtos.response.*;
import racingcar.repository.RacingCarDao;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaysService {
    private final RacingCarDao racingCarDao;
    private final Random random = new Random();

    public PlaysService(RacingCarDao racingCarDao) {
        this.racingCarDao = racingCarDao;
    }

    public PlaysResponseDto play(PlaysRequestDto playsRequestDto) {
        List<RacingCar> racingCars = Arrays.stream(playsRequestDto.getNames().split(","))
                                           .map(RacingCar::new)
                                           .collect(Collectors.toList());

        playRound(racingCars, playsRequestDto.getTrialCount());
        List<String> winners = getWinners(racingCars);
        Long latestGame = getLatestGame();
        winners.forEach(winner -> racingCarDao.insertWinner(winner, playsRequestDto.getTrialCount(), latestGame + 1));
        racingCars.forEach(racingCar -> racingCarDao.insertPlayPositionAndGame(racingCar.getName(), racingCar.getPosition(), latestGame + 1));
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

    private Long getLatestGame() {
        return racingCarDao.selectLatestGame().orElseGet(() -> 0L);
    }

    public List<PlayHistories> getPlayHistories() {
        //경기를 기준으로 조회
        Map<Long, List<PlayResultWinnersAndGame>> winnersGames = racingCarDao.getWinnersAndGames();
        Map<Long, List<PlayFinalPositionAndGame>> allPlayFinalTravelDistance = racingCarDao.getAllPlayFinalPositionAndGame();

        return getTotalOfGame().stream()
                               .map(game -> {
                                   PlayHistories histories = new PlayHistories();
                                   histories.setWinners(winnersGames.get(game)
                                                                    .stream()
                                                                    .map(PlayResultWinnersAndGame::getWinners)
                                                                    .collect(Collectors.joining(",")));

                                   histories.setRacingCars(allPlayFinalTravelDistance.get(game)
                                                                                     .stream()
                                                                                     .map(ftd -> new PlayRacingCar(ftd.getName(), ftd.getPosition()))
                                                                                     .collect(Collectors.toList())
                                   );
                                   return histories;
                               }).collect(Collectors.toList());
    }

    private List<Long> getTotalOfGame() {
        return racingCarDao.getTotalNumberOfGame();
    }
}
