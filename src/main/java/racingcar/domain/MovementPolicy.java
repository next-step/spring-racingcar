package racingcar.domain;

public class MovementPolicy {
  public static int MAXIMUM_RANDOM_BOUNDARY = 10;
  public static int MOVEMENT_RANDOM_BOUNDARY = 4;

  public MovementAction action() {
    return RandomNumber.generate(MAXIMUM_RANDOM_BOUNDARY) >= MOVEMENT_RANDOM_BOUNDARY
        ? MovementAction.MOVE
        : MovementAction.STOP;
  }
}
