package racingcar.infra;

import racingcar.domain.PlayResult;
import racingcar.domain.PlayResultRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayResultCacheDao implements PlayResultRepository {

    private static final int INITIAL_KEY = 1;
    private static final Map<Integer, PlayResult> CACHE = new HashMap<>();

    @Override
    public int save(PlayResult playResult) {
        int key = CACHE.keySet().stream()
                .max(Integer::compareTo)
                .map(it -> it + 1)
                .orElse(INITIAL_KEY);
        playResult.updateId(key);
        CACHE.put(key, playResult);

        return key;
    }

    @Override
    public List<PlayResult> findAll() {
        return new ArrayList<>(CACHE.values());
    }

}
