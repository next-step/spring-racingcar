package racingcar.model;

public class RacingRequest {

    private String names;

    private int count;

    public void validate() {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름 입력 필요");
        }

        if (count <= 0) {
            throw new IllegalArgumentException("시도 횟수 입력 필요");
        }
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
