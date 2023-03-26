package racingcar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import racingcar.domain.PlayResult;

public interface PlayResultRepository extends JpaRepository<PlayResult, Long> {
    PlayResult save(PlayResult playResult);

    Optional<PlayResult> findById(Long id);

    List<PlayResult> findAll();
}
