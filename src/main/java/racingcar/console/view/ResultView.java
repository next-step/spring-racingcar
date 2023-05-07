package racingcar.console.view;

import racingcar.domain.dto.PlayResultDto;

import java.util.List;

public class ResultView {

    private static final String CAR_NAME_SEPARATOR = ", ";

    public static void printWinners(String[] carNames) {
        System.out.println("\n우승자 : " + String.join(CAR_NAME_SEPARATOR, carNames));
    }

    public static void printPlayResults(List<PlayResultDto> playResultDtos) {
        System.out.println("\n결과 : ");
        for (PlayResultDto playResultDto : playResultDtos) {
            System.out.println("Name : " + playResultDto.getNameValue() + ", Position : " + playResultDto.getPositionValue());
        }
    }

}
