package racingcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingCarGame;
import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.entity.dao.PlayHistoryDao;
import racingcar.entity.dao.PlayHistoryDetailDao;
import racingcar.web.dto.PlayHistoryDto;
import racingcar.entity.PlayHistory;
import racingcar.entity.PlayHistoryDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayService {

    private final MovingStrategy movingStrategy;
    private final PlayHistoryDao playHistoryDao;
    private final PlayHistoryDetailDao playHistoryDetailDao;

    public List<PlayResultDto> play(String[] carNames, int playCount) {
        RacingCarGame racingCarGame = new RacingCarGame(carNames, playCount);
        List<PlayResultDto> playResultDtos = null;

        while (!racingCarGame.isEnd()) {
            racingCarGame.play(movingStrategy);
            playResultDtos = racingCarGame.getPlayResults();
        }

        return playResultDtos;
    }

    public String[] findWinners(List<PlayResultDto> playResultDtos) {
        return RacingCarGame.findWinners(playResultDtos).stream()
                .map(PlayResultDto::getNameValue)
                .toArray(String[]::new);
    }

    @Transactional
    public Long savePlayResults(int playCount, String winners, List<PlayResultDto> playResultDtos) {
        PlayHistory playHistory = new PlayHistory(playCount, winners);
        Long playHistoryId = playHistoryDao.save(playHistory);

        List<PlayHistoryDetail> playHistoryDetails = playResultDtos.stream()
                .map(playResult -> playResult.toPlayHistoryDetail(playHistoryId))
                .collect(Collectors.toList());

        playHistoryDetailDao.saveAll(playHistoryDetails);

        return playHistoryId;
    }

    @Transactional(readOnly = true)
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
