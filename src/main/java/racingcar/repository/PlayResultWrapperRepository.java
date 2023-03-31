package racingcar.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;
import racingcar.domain.PlayResult;

@RequiredArgsConstructor
public class PlayResultWrapperRepository {
    private final JpaRepository<PlayResult, Long> playResultRepository;

    @Transactional
    public PlayResult save(PlayResult playResult) {
        return playResultRepository.save(playResult);
    }

    @Transactional
    public Optional<PlayResult> findById(Long id) {
        return playResultRepository.findById(id);
    }

    @Transactional
    public List<PlayResult> findAll() {
        return playResultRepository.findAll();
    }
}
