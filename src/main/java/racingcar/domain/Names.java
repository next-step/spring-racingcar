package racingcar.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Names {
  private final List<String> values;
  private static final String CAR_NAME_DELIMITER = ",";

  public Names(String names) {
    this.values = generate(splitByDelimiter(names));
  }

  private String[] splitByDelimiter(String names) {
    return names.split(CAR_NAME_DELIMITER);
  }

  private List<String> generate(String[] splitNames) {
    return Arrays.stream(splitNames)
        .map(String::trim)
        .collect(Collectors.toList());
  }
}
