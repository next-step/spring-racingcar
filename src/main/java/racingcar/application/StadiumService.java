package racingcar.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayCarDao;
import racingcar.domain.Car;
import racingcar.domain.CarCollection;
import racingcar.dto.PlaysDto;
import racingcar.dto.RacingCarResultDto;

import java.util.List;

@Service
@Transactional
public class StadiumService {

    private final PlayCarDao playCarDao;

    public StadiumService(PlayCarDao playCarDao) {
        this.playCarDao = playCarDao;
    }

    public RacingCarResultDto playRacingResult(PlaysDto playsDto){

        int id = playCarDao.insertPlayhistory(playsDto.getCount());
        Stadium stadium = new Stadium(new CarCollection(playsDto.getNames()), playsDto.getCount());

        RacingCarResultDto resultDto = stadium.playRacingCar();

        List<Car> cars = stadium.getCars();

        cars.stream().forEach(car -> playCarDao.insertPlayCarHistory(id,car));
        playCarDao.insertPlayResult(id, resultDto.getWinners());

        return resultDto;
    }


    public List<RacingCarResultDto> allPlays(){
        return playCarDao.getAllRacingResult();
    }


}
