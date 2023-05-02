package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class Positions {

  private final List<Integer> values;

  public Positions(List<Integer> positionList) {
    this.values = positionList;
  }

  public Integer getMaximumPosition() {
    return Collections.max(values);
  }
}
