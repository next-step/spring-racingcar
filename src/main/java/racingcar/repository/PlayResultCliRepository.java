package racingcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import racingcar.domain.PlayResult;

public interface PlayResultCliRepository
        extends JpaRepository<PlayResult, Long>, PlayResultCliCustomRepository<PlayResult> {
}