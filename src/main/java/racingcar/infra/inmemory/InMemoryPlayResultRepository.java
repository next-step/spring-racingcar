package racingcar.infra.inmemory;

import racingcar.domain.entity.PlayResult;
import racingcar.domain.repository.PlayResultRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPlayResultRepository implements PlayResultRepository {

    private List<PlayResult> playResults = new ArrayList<>();

    @Override
    public void insert(PlayResult result) {
        playResults.add(result);
    }

    @Override
    public List<PlayResult> getAllPlayResults() {
        return playResults;
    }
}
