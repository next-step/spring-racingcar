package racingcar.repository;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.PlayResult;

@Transactional
public class PlayResultCliCustomRepositoryImpl implements PlayResultCliCustomRepository<PlayResult> {
    private final EntityManager em;

    public PlayResultCliCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public PlayResult save(PlayResult playResult) {
        return null;
    }

}
