package racingcar.infra;

import racingcar.domain.PlayHistory;
import racingcar.domain.PlayHistoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayHistoryCacheDao implements PlayHistoryRepository {

    private static final List<PlayHistory> CACHE = new ArrayList<>();

    @Override
    public void save(List<PlayHistory> playHistories) {
        CACHE.addAll(playHistories);
    }

    @Override
    public List<PlayHistory> findAll() {
        return Collections.unmodifiableList(CACHE);
    }

}
