package racingcar.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.service.RacingCarGameService;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.web.dao.PlayHistoryDao;
import racingcar.web.dao.PlayHistoryDetailDao;
import racingcar.web.dto.PlayHistoryDto;
import racingcar.web.entity.PlayHistory;
import racingcar.web.entity.PlayHistoryDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayService {

    private final PlayHistoryDao playHistoryDao;
    private final PlayHistoryDetailDao playHistoryDetailDao;
    private final RacingCarGameService racingCarGameService;

    @Autowired
    public PlayService(MovingStrategy movingStrategy, PlayHistoryDao playHistoryDao, PlayHistoryDetailDao playHistoryDetailDao) {
        this.playHistoryDao = playHistoryDao;
        this.playHistoryDetailDao = playHistoryDetailDao;

        this.racingCarGameService = new RacingCarGameService(movingStrategy);
    }

    public List<PlayResultDto> play(String[] carNames, int playCount) {
        return racingCarGameService.play(carNames, playCount);
    }

    public String[] findWinners(List<PlayResultDto> playResultDtos) {
        return racingCarGameService.findWinners(playResultDtos);
    }

    @Transactional
    public Long savePlayResults(int playCount, String winners, List<PlayResultDto> playResultDtos) {
        PlayHistory playHistory = new PlayHistory(playCount, winners);
        Long playHistoryId = playHistoryDao.save(playHistory);

        List<PlayHistoryDetail> playHistoryDetails = playResultDtos.stream()
                .map(playResult -> playResult.toPlayHistoryDetail(playHistoryId))
                .collect(Collectors.toList());

        for (PlayHistoryDetail playHistoryDetail : playHistoryDetails) {
            playHistoryDetailDao.save(playHistoryDetail);
        }

        return playHistoryId;
    }

    public List<PlayHistoryDto> history() {
        List<PlayHistoryDto> results = new ArrayList<>();

        List<PlayHistory> playHistorys = playHistoryDao.findAll();
        for (PlayHistory playHistory : playHistorys) {
            List<PlayHistoryDetail> playHistoryDetails = playHistoryDetailDao.findByPlayHistoryId(playHistory.getId());
            List<PlayHistoryDto.RacingCar> racingCars = playHistoryDetails.stream()
                    .map(PlayHistoryDetail::toPlayHistoryDtoRacingCar)
                    .collect(Collectors.toList());

            results.add(new PlayHistoryDto(playHistory.getWinners(), racingCars));
        }

        return results;
    }

}
