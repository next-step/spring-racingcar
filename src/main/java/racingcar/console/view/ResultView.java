package racingcar.console.view;

import racingcar.domain.dto.PlayResultDto;

import java.util.List;

public class ResultView {

    public static void printWinners(String winners) {
        System.out.println("\n우승자 : " + winners);
    }

    public static void printPlayResults(List<PlayResultDto> playResultDtos) {
        System.out.println("\n결과 : ");
        for (PlayResultDto playResultDto : playResultDtos) {
            System.out.println("Name : " + playResultDto.getNameValue() + ", Position : " + playResultDto.getPositionValue());
        }
    }

}
