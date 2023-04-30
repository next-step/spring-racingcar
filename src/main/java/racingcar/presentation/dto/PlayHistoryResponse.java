package racingcar.presentation.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import racingcar.domain.PlayHistory;
import racingcar.domain.PlayResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayHistoryResponse {

    @JsonValue
    private List<PlayResponse> histories;

    private PlayHistoryResponse(List<PlayResponse> histories) {
        this.histories = histories;
    }

    public static PlayHistoryResponse of(List<PlayResult> playResults, List<PlayHistory> playHistories) {
        Map<Integer, List<PlayHistory>> historyByGameId = playHistories.stream()
                .collect(Collectors.groupingBy(PlayHistory::getPlayResultId));

        List<PlayResponse> responses = playResults.stream()
                .map(it -> PlayResponse.of(it, historyByGameId.get(it.getId())))
                .collect(Collectors.toList());

        return new PlayHistoryResponse(responses);
    }

}
