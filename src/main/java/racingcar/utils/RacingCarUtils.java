package racingcar.utils;

import java.util.List;

public class RacingCarUtils {
    private RacingCarUtils() {
    }

    public static List<String> stringToList(String names) {
        return List.of(names.split(","))
                .stream()
                .map(String::trim)
                .toList();
    }

}
