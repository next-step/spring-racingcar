package racingcar.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCars;
import racingcar.domain.dto.RacingGameResultDto;
import racingcar.domain.repository.PlayResultRepository;
import racingcar.domain.repository.RacingCarRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacingCarService {

    private final PlayResultRepository playResultRepository;

    @Transactional
    public RacingGameResultDto plays(String names, Integer count) {
        RacingCars racingCars = new RacingCars(names);

        // 경주 시작
        racingCars.playRound(count);

        // 결과 저장
        PlayResult playResult = PlayResult.builder()
                .trialCount(count)
                .winners(racingCars.findWinners())
                .racingCars(racingCars)
                .createdAt(LocalDateTime.now())
                .build();
        playResultRepository.insert(playResult);

        return RacingGameResultDto.from(playResult);
    }

    @Transactional
    public List<RacingGameResultDto> getHistory() {
        List<PlayResult> playResults = playResultRepository.findAll();

        return playResults.stream()
                .map(RacingGameResultDto::from)
                .collect(Collectors.toList());
    }
}
