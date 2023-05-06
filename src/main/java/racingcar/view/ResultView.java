package racingcar.view;

import racingcar.domain.PlayResult;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static void printWinners(List<PlayResult> playResults) {
        String winnerCarNames = playResults.stream()
                .map(PlayResult::getNameValue)
                .collect(Collectors.joining(", "));
        System.out.println("\n우승자 : " + winnerCarNames);
    }

    public static void printPlayResults(List<PlayResult> playResults) {
        System.out.println("\n결과 : ");
        for (PlayResult playResult : playResults) {
            System.out.println("Name : " + playResult.getNameValue() + ", Position : " + playResult.getPositionValue());
        }
    }

}
