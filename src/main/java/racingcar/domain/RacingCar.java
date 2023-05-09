package racingcar.domain;

public class RacingCar {

  private final String name;
  private int position;

  public RacingCar(String name) {
    this.name = name;
    this.position = 0;
  }

  public RacingCar(String name, int position) {
    this.name = name;
    this.position = position;
  }

  public String getName() {
    return this.name;
  }

  public int getPosition() {
    return this.position;
  }

  public void move(int randomNumber) {
    if (randomNumber >= 4) {
      this.position++;
    }
  }
}