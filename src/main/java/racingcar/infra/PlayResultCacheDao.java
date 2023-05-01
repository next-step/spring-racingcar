package racingcar.infra;

import racingcar.domain.PlayResult;
import racingcar.domain.PlayResultRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayResultCacheDao implements PlayResultRepository {

    private static final int KEY = 0;
    private static final Map<Integer, PlayResult> CACHE = new HashMap<>();

    @Override
    public int save(PlayResult playResult) {
        AtomicInteger key = new AtomicInteger(KEY);
        int keyValue = key.incrementAndGet();
        playResult.updateId(keyValue);
        CACHE.put(keyValue, playResult);

        return keyValue;
    }

    @Override
    public List<PlayResult> findAll() {
        return new ArrayList<>(CACHE.values());
    }

}
