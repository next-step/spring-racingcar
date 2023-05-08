package racingcar.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Names {
  private final List<String> values;

  public Names(String names, String delimiter) {
    this.values = generate(splitByDelimiter(names, delimiter));
  }

  private String[] splitByDelimiter(String names, String delimiter) {
    return names.split(delimiter);
  }

  private List<String> generate(String[] splitNames) {
    return Arrays.stream(splitNames)
        .map(String::trim)
        .collect(Collectors.toList());
  }
}
