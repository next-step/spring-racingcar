package racingcar.domain;

import java.util.Random;

public class RandomNumber {
  public static int generate(int bound) {
    Random random = new Random();
    return random.nextInt(bound);
  }
}
